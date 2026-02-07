package plugins

import com.android.build.gradle.LibraryExtension
import configuration.DependencyConfiguration.configureFeatureDependencies
import configuration.DependencyConfiguration.configureImageDependencies
import configuration.ProjectConfiguration
import extensions.configureAndroidCompose
import extensions.configureHilt
import extensions.configureKotlinAndroid
import extensions.configureTesting
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                namespace = getFeatureNamespace()
                
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }

            configureHilt()
            configureFeatureDependencies()
            configureImageDependencies() // TODO: remove this after demo
            configureTesting()
        }
    }

    private fun Project.getFeatureNamespace(): String {
        val featureName = path.removePrefix(":feature:")
        return when (featureName) {
            "genre" -> ProjectConfiguration.Namespace.Feature.GENRE
            "movie" -> ProjectConfiguration.Namespace.Feature.MOVIE
            else -> "${ProjectConfiguration.Namespace.Feature.GENRE}.$featureName" // fallback
        }
    }
}