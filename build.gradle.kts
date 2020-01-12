import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.google.protobuf") version "0.8.8"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.0" apply false
}

allprojects {
    repositories {
        jcenter()
    }

    group = "com.hojongs"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
        implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.11")
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

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        disabledRules.add("import-ordering")
    }
}
