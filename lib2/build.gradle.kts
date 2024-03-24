import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //alias(libs.plugins.kotlinMultiplatform)
    kotlin("multiplatform")
}

kotlin {
    jvm()
    linuxX64()

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
        //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("io.ktor:ktor-server-core:2.2.4")
        implementation("io.ktor:ktor-server-cio:2.2.4")
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        //implementation(kotlin("stdlib-jdk8"))
        implementation("ch.qos.logback:logback-classic:1.2.13")
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
        //implementation(libs.kotlin.test)
        //implementation(libs.junit5.api)
        //implementation(libs.junit5.impl)
        //implementation(libs.junit5.params)
    }

    linuxX64().apply {
        binaries {
            executable {
                entryPoint = "com.kaizensundays.fusion.main"
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
    kotlinOptions.jvmTarget = "1.8"
}