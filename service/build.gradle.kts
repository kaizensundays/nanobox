import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("com.jfrog.artifactory")
    `maven-publish`
}

group = "com.kaizensundays.fusion.nanobox"
version = "0.0.1-SNAPSHOT"

dependencies {

    implementation(project(":core"))

    implementation(libs.spring.context)

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Test> {
    useJUnitPlatform()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.bootJar {
    archiveFileName.set("$bundleName.jar")
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
                artifactId = "nanobox"
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
