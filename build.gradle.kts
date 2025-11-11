plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
    war
}

group = "egov"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.egovframe.go.kr/maven/")
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // eGovFrame dependencies
    implementation("org.egovframe.rte:org.egovframe.rte.ptl.mvc:4.3.0")
    implementation("org.egovframe.rte:org.egovframe.rte.psl.dataaccess:4.3.0")
    implementation("org.egovframe.rte:org.egovframe.rte.fdl.idgnr:4.3.0")
    implementation("org.egovframe.rte:org.egovframe.rte.fdl.property:4.3.0")
    implementation("org.egovframe.rte:org.egovframe.rte.fdl.crypto:4.3.0")

    implementation("org.egovframe.rte:org.egovframe.rte.fdl.security:4.3.0") {
        exclude(group = "org.springframework", module = "spring-jdbc")
    }

    // Spring Security
    implementation("org.springframework.security:spring-security-core:5.7.11")
    implementation("org.springframework.security:spring-security-config:5.7.11")
    implementation("org.springframework.security:spring-security-web:5.7.11")

    // Database
    implementation("com.h2database:h2:2.1.214")
    implementation("com.mysql:mysql-connector-j:8.0.33")
    implementation("org.apache.commons:commons-dbcp2:2.12.0")

    // Servlet & JSP
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("javax.servlet.jsp:javax.servlet.jsp-api:2.3.3")
    implementation("javax.servlet.jsp.jstl:jstl-api:1.2")
    implementation("org.apache.taglibs:taglibs-standard-impl:1.2.5")

    // Utilities
    implementation("commons-codec:commons-codec:1.17.0")
    implementation("cglib:cglib:3.3.0")
    implementation("org.antlr:antlr:3.5")
    implementation("org.apache.commons:commons-compress:1.26.2")
    implementation("commons-fileupload:commons-fileupload:1.5")
    implementation("com.ibm.icu:icu4j:75.1")
    implementation("org.jdom:jdom2:2.0.6.1")

    // JSON processing
    implementation("org.eclipse:yasson:1.0.2")
    implementation("org.glassfish:javax.json:1.1.4")

    // Logging
    implementation("com.googlecode.log4jdbc:log4jdbc:1.2") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }

    // Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Development tools
    // compileOnly("org.projectlombok:lombok:1.18.34")
    // annotationProcessor("org.projectlombok:lombok:1.18.34")

    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.13.0")
    testImplementation("org.springframework:spring-test:5.3.37")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<JavaCompile> {
    options.release.set(11)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.war {
    archiveBaseName.set("pst_webapp")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// Configure source sets to include both Java and Kotlin
sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
    test {
        java.srcDirs("src/test/java", "src/test/kotlin")
        resources.srcDirs("src/test/resources")
    }
}

// Copy database scripts to resources
tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from("DATABASE/oracle") {
        include("all_pst_ddl_oracle.sql")
        into("db")
    }
    from("DATABASE/mysql") {
        include("all_pst_data_mysql.sql")
        into("db")
    }
}