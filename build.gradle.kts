plugins {
    //id("root.publication")
    //alias(libs.plugins.kotlinMultiplatform).apply(false)
    kotlin("multiplatform") version "1.8.22" apply false
    kotlin("jvm") version "1.8.22" apply false
    id("org.springframework.boot") version "2.7.18" apply false
    id("com.jfrog.artifactory") version "4.29.0" apply false
}
