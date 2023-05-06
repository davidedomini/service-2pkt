plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "it.unibo"

repositories {
    mavenCentral()
}

dependencies {
    implementation("it.unibo.tuprolog:full:0.31.3")
    implementation("com.rabbitmq:amqp-client:5.9.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("it.unibo.MainKt")
}