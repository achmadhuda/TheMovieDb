package extensions

import com.android.build.api.dsl.CommonExtension
import configuration.DependencyConfiguration.getVersionCatalog
import configuration.ProjectConfiguration
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ProjectConfiguration.COMPILE_SDK

        defaultConfig {
            minSdk = ProjectConfiguration.MIN_SDK
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = ProjectConfiguration.JAVA_VERSION
            targetCompatibility = ProjectConfiguration.JAVA_VERSION
        }

        packaging {
            resources {
                excludes += ProjectConfiguration.Packaging.EXCLUDED_RESOURCES
            }
        }

        buildFeatures {
            buildConfig = true
        }

        if (this is com.android.build.gradle.LibraryExtension) {
            defaultConfig.targetSdk = ProjectConfiguration.TARGET_SDK
            defaultConfig.consumerProguardFiles(ProjectConfiguration.ProGuard.CONSUMER_RULES)
            defaultConfig.testInstrumentationRunner = ProjectConfiguration.TEST_INSTRUMENTATION_RUNNER
        }
    }

    val libs = getVersionCatalog()
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("desugar-jdk-libs").get())
    }

    configureKotlinCompilerOptions()
}

private fun Project.configureKotlinCompilerOptions() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(ProjectConfiguration.JVM_TARGET)
            freeCompilerArgs.addAll(ProjectConfiguration.CompilerOptions.FREE_COMPILER_ARGS)
        }
    }
}

fun Project.configureAndroidApplication(
    applicationExtension: com.android.build.api.dsl.ApplicationExtension
) {
    applicationExtension.apply {
        compileSdk = ProjectConfiguration.COMPILE_SDK
        
        defaultConfig {
            applicationId = ProjectConfiguration.APPLICATION_ID
            minSdk = ProjectConfiguration.MIN_SDK
            targetSdk = ProjectConfiguration.TARGET_SDK
            versionCode = ProjectConfiguration.VERSION_CODE
            versionName = ProjectConfiguration.VERSION_NAME
            testInstrumentationRunner = ProjectConfiguration.TEST_INSTRUMENTATION_RUNNER
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = ProjectConfiguration.JAVA_VERSION
            targetCompatibility = ProjectConfiguration.JAVA_VERSION
        }

        packaging {
            resources {
                excludes += ProjectConfiguration.Packaging.EXCLUDED_RESOURCES
            }
        }
    }
}