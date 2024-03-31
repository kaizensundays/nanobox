import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

/**
 * Created: Sunday 3/31/2024, 4:26 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Dependencies {

    const val logbackVersion = "1.2.13"
    const val springVersion = "5.3.33"
    const val okioVersion = "3.4.0"
    const val ktorVersion = "2.2.4"

    const val logbackClassic = "ch.qos.logback:logback-classic:$logbackVersion"
    const val springContext = "org.springframework:spring-context:$springVersion"
    const val okio = "com.squareup.okio:okio:$okioVersion"
    const val ktorServerCore = "io.ktor:ktor-server-core:$ktorVersion"
    const val ktorServerCIO = "io.ktor:ktor-server-cio:$ktorVersion"

}

fun KotlinDependencyHandler.logback(): Dependency? {
    return implementation(Dependencies.logbackClassic)
}

fun KotlinDependencyHandler.okio(): Dependency? {
    return implementation(Dependencies.okio)
}

fun KotlinDependencyHandler.ktor() {
    implementation(Dependencies.ktorServerCore)
    implementation(Dependencies.ktorServerCIO)
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.springContext() {
    implementation(Dependencies.springContext)
}