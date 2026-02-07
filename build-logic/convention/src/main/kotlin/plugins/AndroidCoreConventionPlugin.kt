package plugins

import com.android.build.gradle.LibraryExtension
import configuration.DependencyConfiguration.configureImageDependencies
import configuration.DependencyConfiguration.configureLoggingDependencies
import configuration.DependencyConfiguration.configureNetworkingDependencies
import configuration.DependencyConfiguration.configurePagingDependencies
import configuration.DependencyConfiguration.configurePersistenceDependencies
import configuration.ProjectConfiguration
import extensions.configureAndroidCompose
import extensions.configureHilt
import extensions.configureKotlinAndroid
import extensions.configureTesting
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidCoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                namespace = ProjectConfiguration.Namespace.CORE

                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                defaultConfig {
                    buildConfigField("int", "VERSION_CODE", "${ProjectConfiguration.VERSION_CODE}")
                }

                buildFeatures {
                    buildConfig = true
                }
            }

            configureHilt()
            configureNetworkingDependencies()
            configureImageDependencies()
            configurePersistenceDependencies()
            configurePagingDependencies()
            configureLoggingDependencies()
            configureTesting()
        }
    }
}