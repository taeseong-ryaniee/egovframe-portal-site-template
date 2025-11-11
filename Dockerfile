# Multi-stage build: build WAR from src, then run on Tomcat

FROM maven:3.8.8-eclipse-temurin-8 AS build
WORKDIR /app

# Cache dependencies first
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
COPY DATABASE ./DATABASE
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package

FROM tomcat:9.0-jdk8-temurin AS runtime

ENV TZ=Asia/Seoul \
    CATALINA_HOME=/usr/local/tomcat

WORKDIR ${CATALINA_HOME}

# Deploy only as ROOT to avoid duplicated contexts
RUN rm -rf webapps/ROOT webapps/pst_webapp.war
COPY --from=build /app/target/pst_webapp.war webapps/ROOT.war

# Ensure upload directory exists (mounted at runtime via volume)
RUN mkdir -p /user/file/sht/

EXPOSE 8080

CMD ["bash", "-lc", "until getent hosts mysql >/dev/null; do echo '[WAIT] resolving mysql...'; sleep 1; done; catalina.sh run"]
