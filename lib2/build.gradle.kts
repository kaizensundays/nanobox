import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.8.22"
}

kotlin {
    jvm()

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }
}

tasks.withType<Test> {
    useJUnit()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}