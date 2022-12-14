val ktor_version = "2.1.1"
val kotlin_version = "1.7.10"
val logback_version = "1.2.11"
val postgres_version = "42.3.3"

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
    id("com.ncorti.ktfmt.gradle") version "0.10.0"
}

ktfmt { kotlinLangStyle() }

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17 // new
    targetCompatibility = JavaVersion.VERSION_17 // new
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
