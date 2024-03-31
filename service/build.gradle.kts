import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("com.jfrog.artifactory")
    id("module.publication")
}

group = "com.kaizensundays.fusion"
version = "0.0.1-SNAPSHOT"

val springVersion: String by project

dependencies {

    implementation(project(":lib2"))
    implementation("org.springframework:spring-context:$springVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.bootJar {
    archiveFileName.set("service.jar")
    destinationDirectory.set(file("bin"))
}

tasks.publish {
    dependsOn("assemble")
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("bootJava") {
            from(components["java"])
            artifact(tasks.bootJar) {
                artifactId = "nano-box"
            }
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}

artifactory {
    setContextUrl(project.properties["artifactory.url"] as String)
    publish {
        repository {
            setRepoKey("libs-snapshot-local")
            setUsername(project.properties["artifactory.username"] as String)
            setPassword(project.properties["artifactory.password"] as String)
            setMavenCompatible(true)
        }
        defaults {
            publications("bootJava")
            setPublishArtifacts(true)
            setPublishPom(true)
        }
    }
}
