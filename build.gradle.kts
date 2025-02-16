plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

val kotestVersion = "5.5.5" // 추가!

val mockkVersion = "1.13.8" // 추가!
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	runtimeOnly("com.h2database:h2")

	implementation("org.jetbrains.exposed:exposed-core:0.59.0")
	implementation("org.jetbrains.exposed:exposed-crypt:0.59.0")
	implementation("org.jetbrains.exposed:exposed-dao:0.59.0")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.59.0")

//	implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.59.0")
	implementation("org.jetbrains.exposed:exposed-java-time:0.59.0")

	implementation("org.jetbrains.exposed:exposed-json:0.59.0")
	implementation("org.jetbrains.exposed:exposed-money:0.59.0")
	implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.59.0")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}
tasks.withType<Test> {
	useJUnitPlatform()
}
