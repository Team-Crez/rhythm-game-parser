
val kotlin_version = "1.6.10"

plugins {
    java
    `java-library`
    kotlin("jvm") version "1.6.10"
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

    compileOnly ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

}