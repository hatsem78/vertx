import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
  id("io.spring.dependency-management") version "1.0.1.RELEASE"
}

group = "com.octaviohatsembiller.com"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}


val vertxVersion = "5.0.0.CR4"
val junitJupiterVersion = "5.9.1"
var log4jVersion = "1.7.30"

val mainVerticleName = "com.octaviohatsembiller.com.vertx_starter.MainVerticle"
val launcherClassName = "io.vertx.launcher.application.VertxApplication"


val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencyManagement {
  imports {
    mavenBom("or.apache.logging.log4j:log4j-bom:2.14.0")
  }
}

dependencies {

  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("org.apache.logging.log4j:log4j-api")
  implementation("org.apache.logging.log4j:log4j-core")
  implementation("org.apache.logging.log4j:log4j-slf4j-impl")
  implementation("org.slf4j:slf4j-api:$log4jVersion")

  implementation("io.vertx:vertx-core")
  implementation("io.vertx:vertx-launcher-application")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")

}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf(mainVerticleName)
}
