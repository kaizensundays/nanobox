plugins {
    kotlin("multiplatform") version KotlinVersion apply false
    kotlin("jvm") version KotlinVersion apply false
    kotlin("plugin.serialization") version KotlinVersion apply false
    kotlin("plugin.spring") version KotlinVersion apply false
    id("org.springframework.boot") version SpringBootVersion apply false
    id("com.jfrog.artifactory") version JFrogAartifactoryPluginVersion apply false
}
