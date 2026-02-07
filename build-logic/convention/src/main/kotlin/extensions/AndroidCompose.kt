package extensions

import com.android.build.api.dsl.CommonExtension
import configuration.DependencyConfiguration.configureComposeDependencies
import org.gradle.api.Project

fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
    
    configureComposeDependencies()
}