import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.themoviedb.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.serialization.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = "themoviedb.android.app"
            implementationClass = "plugins.AndroidAppConventionPlugin"
        }
        register("androidFeature") {
            id = "themoviedb.android.feature"
            implementationClass = "plugins.AndroidFeatureConventionPlugin"
        }
        register("androidCore") {
            id = "themoviedb.android.core"
            implementationClass = "plugins.AndroidCoreConventionPlugin"
        }
        register("androidHilt") {
            id = "themoviedb.android.hilt"
            implementationClass = "plugins.AndroidHiltConventionPlugin"
        }
    }
}