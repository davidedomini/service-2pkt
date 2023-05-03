plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "it.unibo"

repositories {
    mavenCentral()
}

dependencies {
    implementation("it.unibo.tuprolog:solve:0.31.5")
    implementation("it.unibo.tuprolog:solve-classic:0.31.5")
    implementation("it.unibo.tuprolog:core:0.31.5")
    implementation("it.unibo.tuprolog:theory:0.31.5")
    implementation("it.unibo.tuprolog:unify:0.31.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}