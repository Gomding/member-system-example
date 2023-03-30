import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.31"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    application
}

group = "com.member"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

subprojects {

    apply {
        plugin("kotlin")
        plugin("io.spring.dependency-management")
        plugin("kotlin-spring") // instead of "kotlin-allopen"
        plugin("org.springframework.boot")
        plugin("kotlin-jpa")
        plugin("jacoco")
        plugin("kotlin-kapt")
        plugin("idea")
    }

    dependencies {
        // web
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // persistence
        runtimeOnly("com.h2database:h2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        // test
        testImplementation(kotlin("test"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.junit.jupiter", "junit-jupiter", "5.8.2")
        testImplementation("io.kotest", "kotest-runner-junit5", "5.3.1")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("member-system-example.jar")
    mainClass.set("MemberSystemExampleApplicationKt")
}