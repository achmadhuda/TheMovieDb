package plugins

import com.android.build.api.dsl.ApplicationExtension
import configuration.BuildTypeConfiguration.configureApplicationBuildTypes
import configuration.BuildTypeConfiguration.configureBuildConfigFields
import configuration.DependencyConfiguration.configureAppDependencies
import configuration.DependencyConfiguration.configureCoreAndroidDependencies
import configuration.DependencyConfiguration.configureImageDependencies
import configuration.DependencyConfiguration.configureNavigationDependencies
import configuration.LocalPropsService
import configuration.ProjectConfiguration
import extensions.configureAndroidCompose
import extensions.configureHilt
import extensions.configureKotlinAndroid
import extensions.configureTesting
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val propsProvider = gradle.sharedServices.registerIfAbsent(
                "localProps", LocalPropsService::class.java
            ) {}

            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<ApplicationExtension> {
                val props = propsProvider.get()
                namespace = ProjectConfiguration.Namespace.APP
                
                defaultConfig {
                    applicationId = ProjectConfiguration.APPLICATION_ID
                    targetSdk = ProjectConfiguration.TARGET_SDK
                    versionCode = ProjectConfiguration.VERSION_CODE
                    versionName = ProjectConfiguration.VERSION_NAME
                    testInstrumentationRunner = ProjectConfiguration.TEST_INSTRUMENTATION_RUNNER
                }
                
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureApplicationBuildTypes(props, target)
                configureBuildConfigFields()
            }

            configureHilt()
            configureCoreAndroidDependencies()
            configureAppDependencies()
            configureNavigationDependencies()
            configureTesting()
            configureImageDependencies()
        }
    }
}