
group = "com.github.lapsya"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.2.31"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.2.31"))
    testImplementation("junit:junit:4.12")

    compile("khttp:khttp:0.1.0")

    compile ("com.github.magneticflux:kotlin-simplexml-rss:1.1.2")
}