plugins {
    id("root.publication")
    // the same plugin versions in all sub-modules
    //alias(libs.plugins.kotlinMultiplatform).apply(false)
    kotlin("multiplatform") version "1.8.22" apply false
}
