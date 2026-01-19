plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.kotlinSpring) apply false
    id("org.springframework.boot") version SpringBootVersion apply false
    id("com.jfrog.artifactory") version JFrogArtifactoryPluginVersion apply false
    //id("app.cash.sqldelight") version "2.2.1" apply false
}
