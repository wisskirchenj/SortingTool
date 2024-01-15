plugins {
    java
}

repositories {
    mavenCentral()
}

java.toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
}

group = "de.cofinpro"
version = "0.4-SNAPSHOT"

dependencies {
    val log4jVersion = "2.22.0"
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jVersion")
    implementation("org.apache.pdfbox:pdfbox:2.0.24")

    val lombokVersion = "1.18.30"
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.6.0")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}