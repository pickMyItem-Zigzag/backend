import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	id("com.expediagroup.graphql") version "4.0.0-alpha.12"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

group = "kakaostyle"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}


val graphqlIntrospectSchema by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLIntrospectSchemaTask::class) {
	endpoint.set("http://localhost:8080/graphql")
}

val graphqlDownloadSDL by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLDownloadSDLTask::class) {
	endpoint.set("http://localhost:8080/sdl")
}

val graphqlGenerateClient by tasks.getting(com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateClientTask::class) {
	packageName.set("com.example.generated")
	schemaFileName.set("${project.projectDir}/src/main/resources/graphql/schema.graphqls")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:11.0.0")
	implementation("com.graphql-java-kickstart:playground-spring-boot-starter:11.0.0")
	implementation("com.graphql-java-kickstart:graphql-java-tools:11.0.0")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("com.graphql-java-kickstart:graphql-spring-boot-starter-test:11.0.0")
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
