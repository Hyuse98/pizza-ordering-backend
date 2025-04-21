plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.hyuse"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

extra["springModulithVersion"] = "1.3.4"

repositories {
	mavenCentral()
}

dependencies {

	//Modulith
	implementation("org.springframework.modulith:spring-modulith-starter-core")
	implementation("org.springframework.modulith:spring-modulith-starter-jpa")
	implementation("org.springframework.modulith:spring-modulith-docs")
//	implementation("org.springframework.modulith:spring-modulith-starter-mongodb")
//	runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
//	runtimeOnly("org.springframework.modulith:spring-modulith-observability")
	runtimeOnly("org.springframework.modulith:spring-modulith-events-amqp")
	testImplementation("org.springframework.modulith:spring-modulith-starter-test")

	//Core
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	//Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	//Data
	runtimeOnly("com.h2database:h2")
	//implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	//runtimeOnly("org.postgresql:postgresql")
	implementation("org.flywaydb:flyway-core")
	//implementation("org.flywaydb:flyway-database-postgresql")
	//implementation("org.springframework.boot:spring-boot-starter-batch")

	//Infra
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	//Tracin, Metrics, Logs
	//implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("io.micrometer:micrometer-tracing-bridge-brave")
	//implementation("io.zipkin.reporter2:zipkin-reporter-brave")
	//runtimeOnly("io.micrometer:micrometer-registry-otlp")
	//runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	//Broker
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	testImplementation("org.springframework.amqp:spring-rabbit-test")

	//Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	//testImplementation("org.springframework.batch:spring-batch-test")

}

dependencyManagement {
	imports {
		mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
	}
}

tasks.register<JavaExec>("modulithStructure") {
	group = "modulith"
	description = "Gera a documentação de módulos do Spring Modulith"
	classpath = sourceSets["main"].runtimeClasspath
	mainClass.set("com.hyuse.pizzaOrderingBackend.PizzaOrderingBackendApplication")
	args("--modulith --spring.profiles.active=dev")
}

tasks.register<JavaExec>("generateModulithDocs") {
	group = "modulith"
	description = "Gera documentação de módulos Spring Modulith"
	classpath = sourceSets["main"].runtimeClasspath
	mainClass.set("com.hyuse.pizzaOrderingBackend.PizzaOrderingBackendApplication")
	args("--modulith:documents", "--spring.profiles.active=dev")
}


tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
	mainClass.set("com.hyuse.pizzaOrderingBackend.PizzaOrderingBackendApplication")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
