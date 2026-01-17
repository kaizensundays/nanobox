import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.jfrog.artifactory")
    id("dependency-management")
    id("app.cash.sqldelight")
    `maven-publish`
}

kotlin {
    jvm()
    linuxX64()

    sourceSets["commonMain"].dependencies {
        implementation(libs.kotlinx.serialization)
        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.cio)
        implementation(libs.squareup.okio)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        implementation(libs.logback.classic)
        implementation("app.cash.sqldelight:sqlite-driver:2.2.1")
        implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
        implementation(libs.mockito.kotlin)
    }
    sourceSets.nativeMain.dependencies {
        implementation("app.cash.sqldelight:native-driver:2.2.1")
        implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    }

    linuxX64().apply {
        binaries {
            executable(bundleName) {
                entryPoint = "com.kaizensundays.fusion.nanobox.main"
            }
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.kaizensundays.labs.nanobox")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

println("Publication names:")
publishing.publications.all {
    println(name)
}

artifactory {
    setContextUrl(project.properties["artifactory.url"] as String)
    publish {
        repository {
            repoKey = "libs-snapshot-local"
            username = project.properties["artifactory.username"] as String
            password = project.properties["artifactory.password"] as String
        }
        defaults {
            publications("kotlinMultiplatform","jvm", "linuxX64")
            setPublishArtifacts(true)
            setPublishPom(true)
        }
    }
}
