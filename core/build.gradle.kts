
val kotlin_version = "1.6.10"

plugins {
    java
    `java-library`
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
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
    api("com.googlecode.json-simple:json-simple:1.1.1")
    api("com.google.code.gson:gson:2.8.9")


}