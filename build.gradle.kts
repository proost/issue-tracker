plugins {
    application
    id("org.springframework.boot").version("2.3.0.RELEASE")
    id("io.spring.dependency-management").version("1.0.11.RELEASE")
    id("io.freefair.lombok").version("5.3.0")
}

group = "com.project"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.mariadb.jdbc:mariadb-java-client")
    compileOnly("io.lettuce:lettuce-core")
    compileOnly("org.springframework.session:spring-session-data-redis")

    implementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks {
    test {
        useJUnitPlatform {
            includeEngines("junit-vintage")
        }
    }
}