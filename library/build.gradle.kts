plugins {
    //alias(libs.plugins.kotlinMultiplatform)
    kotlin("multiplatform")
    //id("module.publication")
}

kotlin {
    targetHierarchy.default()
    jvm()
    linuxX64()
    //mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
