plugins {
    application
    id("org.springframework.boot").version("2.3.0.RELEASE")
    id("io.spring.dependency-management").version("1.0.11.RELEASE")
    id("io.freefair.lombok").version("5.3.0")
}

group = "com.proost.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    test {
        useJUnitPlatform()
    }
}