repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:0.20.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.20.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.20.1")
    implementation("com.h2database:h2:1.4.200")
    implementation("ch.qos.logback:logback-core:1.2.3")
    implementation("org.slf4j:slf4j-api:1.7.30")
    testImplementation("org.slf4j:slf4j-simple:1.7.30")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
