import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    group = "com.hojongs"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply {
        plugin("kotlin")
    }

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}
