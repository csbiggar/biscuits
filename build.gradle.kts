plugins {
    kotlin("jvm") version "1.4.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val http4kBomVersion = "3.279.0"

val junitJupiterVersion = "5.3.1"
val junitJupiterEngineVersion = "5.5.1"
val assertJVersion = "3.13.2"
val skyscreamerVersion = "1.5.0"
val kotlinLoggingVersion = "2.0.3"
val jacksonVersion = "2.11.2"
val logbackClassicVersion = "1.2.3"
val logbackEncoderVersion = "6.4"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("org.http4k:http4k-bom:$http4kBomVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-format-jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterEngineVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.skyscreamer:jsonassert:$skyscreamerVersion")

    implementation("ch.qos.logback:logback-classic:$logbackClassicVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
}

application {
    mainClass.set("ApplicationKt")
}

tasks {
    named<Test>("test") {
        useJUnitPlatform()
    }
}