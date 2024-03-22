import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
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
        //implementation(kotlin("stdlib-jdk8"))
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
        //implementation(libs.kotlin.test)
        //implementation(libs.junit5.api)
        //implementation(libs.junit5.impl)
        //implementation(libs.junit5.params)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    filter {
        excludeTestsMatching("*RemoteTest")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}