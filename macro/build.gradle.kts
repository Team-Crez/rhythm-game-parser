
val kotlin_version = "1.6.10"

plugins {
    java
    `java-library`
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "me.brucefreedy.macro.Main"
    }
}
sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
        resources {
            setSrcDirs(listOf("resources"))
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    api ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    api (project(":core"))
}