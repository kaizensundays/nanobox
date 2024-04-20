import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.jfrog.artifactory")
    id("dependency-management")
    `maven-publish`
}

kotlin {
    jvm()
    linuxX64()

    sourceSets["commonMain"].dependencies {
        //implementation(kotlin("stdlib-common"))
        //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        //implementation("io.ktor:ktor-server-core:$ktorVersion")
        //implementation("io.ktor:ktor-server-cio:$ktorVersion")
        implementation(libs.kotlinx.serialization)
        //ktor()
        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.cio)
        //implementation("com.squareup.okio:okio:3.4.0")
        //okio()
        implementation(libs.squareup.okio)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        //implementation(kotlin("stdlib-jdk8"))
        //implementation("ch.qos.logback:logback-classic:$logbackVersion")
        //logback()
        implementation(libs.logback.classic)
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
        implementation(libs.mockito.kotlin)
        //implementation(libs.junit5.api)
        //implementation(libs.junit5.impl)
        //implementation(libs.junit5.params)
    }

    linuxX64().apply {
        binaries {
            executable(bundleName) {
                entryPoint = "com.kaizensundays.fusion.nanobox.linux.main"
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

println("Publication names:")
publishing.publications.all {
    println(name)
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
            publications("kotlinMultiplatform","jvm", "linuxX64")
            setPublishArtifacts(true)
            setPublishPom(true)
        }
    }
}
