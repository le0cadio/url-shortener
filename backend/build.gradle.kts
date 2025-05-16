plugins {
    kotlin("jvm") version "1.8.20"
    id("io.ktor.plugin") version "2.3.3"
    application
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-netty:2.3.3")
    implementation("io.ktor:ktor-server-core:2.3.3")
    implementation("ch.qos.logback:logback-classic:1.4.7")
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-tests:2.3.3")
}

kotlin {
    jvmToolchain(17)
}