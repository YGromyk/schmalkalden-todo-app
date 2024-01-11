
plugins {
    java
    `maven-publish`
    id("org.springframework.boot") version "2.6.2"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.3")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("jakarta.validation:jakarta.validation-api:2.0.1")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.3")
    runtimeOnly("org.postgresql:postgresql:42.3.1")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")


    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
}

val mainClassName = "com.gromyk.projectinfo.ProjectinfoApplication"

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.mainClass.set(mainClassName)
}

group = "com.gromyk"
version = "0.0.1-SNAPSHOT"
description = "projectinfo"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
