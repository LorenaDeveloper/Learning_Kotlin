import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.regex.Pattern.compile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	id ("org.jetbrains.kotlin.plugin.jpa") version "1.6.10"
}

group = "com.KotlinProject"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	compile("net.minidev:json-smart:2.4.7")
	// https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")

	implementation("javax.validation:validation-api:2.0.1.Final")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.6.2")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")

	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	compile("org.springframework.boot:spring-boot-starter-data-jpa:1.3.5.RELEASE")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
