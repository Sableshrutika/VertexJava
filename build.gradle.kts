import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
  id("io.spring.dependency-management") version "1.0.1.RELEASE"
  id("io.ebean") version "12.7.2"
}

group = "com.example"
version = "1.0.0-SNAPSHOT"
apply(plugin = "io.ebean")

repositories {
  mavenCentral()
}

val vertxVersion = "4.5.7"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "com.example.starter.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-core")
  implementation("io.vertx:vertx-web-client")
  implementation("io.vertx:vertx-sql-client-templates")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-mysql-client")
  // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
  implementation ("ch.qos.logback:logback-classic:1.3.1")

  // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
  implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")

  // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
  implementation ("com.mysql:mysql-connector-j:8.0.31")

  // https://mvnrepository.com/artifact/io.ebean/ebean
  implementation("io.ebean:ebean:12.7.2")

  // https://mvnrepository.com/artifact/com.google.code.gson/gson
  implementation("com.google.code.gson:gson:2.8.9")

  implementation( "com.fasterxml.jackson.core:jackson-databind:2.8.5")

  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.8.5")

  // https://mvnrepository.com/artifact/io.vertx/vertx-config
  implementation ("io.vertx:vertx-config:4.3.3")

  implementation("io.vertx:vertx-config-yaml:4.3.3")

  // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
  implementation ("io.reactivex.rxjava3:rxjava:3.1.5")

  // https://mvnrepository.com/artifact/org.projectlombok/lombok
  compileOnly("org.projectlombok:lombok:1.18.20")
  annotationProcessor("org.projectlombok:lombok:1.18.20")

  // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit

  implementation ("com.google.code.gson:gson:2.8.7")
  implementation("com.squareup.retrofit2:retrofit:2.7.1")
  implementation("com.squareup.retrofit2:converter-gson:2.7.1")

  // https://mvnrepository.com/artifact/org.json/json
  implementation ("org.json:json:20220924")

  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
  implementation("com.squareup.okhttp3:logging-interceptor:3.7.0")

}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

//application {
//  // Define the main class for the application.
//  mainClass = "com.example.starter"
//}

tasks.withType<JavaExec> {
//  args = listOf("run", mainVerticleName, "-conf /d/dice_workspace/starter/src/main/resources/config.json","--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
  args = listOf("run", mainVerticleName, "-conf D:\\dice_workspace\\starter\\src\\main\\resources\\config.json","--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")

}
