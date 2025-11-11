# H2 Quickstart (No external DB)

```bash
# 1) Build
mvn clean package -DskipTests

# 2) Run with Docker Tomcat (optional)
docker compose up -d

# 또는 로컬 Tomcat(WAS)에 배포
#   target/pst_webapp.war 를 로컬 Tomcat의 webapps/ROOT.war 로 배포 후 기동

# 3) 접속
# http://localhost:8080
# 로그인: admin / 1  (일반: user1 / 1)
```
