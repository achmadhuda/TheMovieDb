package configuration

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

object DependencyConfiguration {

    fun Project.getVersionCatalog(): VersionCatalog {
        return extensions.getByType<VersionCatalogsExtension>().named("libs")
    }

    fun Project.configureCoreAndroidDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("androidx-core-ktx").get())
        }
    }

    fun Project.configureTestDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("testImplementation", libs.findLibrary("junit").get())
            add("testImplementation", libs.findLibrary("mockk").get())
            add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
            
            add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
        }
    }

    fun Project.configureComposeDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("implementation", libs.findLibrary("androidx-activity-compose").get())
            add("implementation", libs.findLibrary("androidx-ui").get())
            add("implementation", libs.findLibrary("androidx-ui-graphics").get())
            add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
            add("implementation", libs.findLibrary("androidx-material3").get())
            
            add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
            add("debugImplementation", libs.findLibrary("androidx-ui-test-manifest").get())
        }
    }

    fun Project.configureHiltDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("hilt-android").get())
            add("ksp", libs.findLibrary("hilt-compiler").get())
        }
    }

    fun Project.configureFeatureDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", project(":core"))
            add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
            add("implementation", libs.findLibrary("hilt-navigation-compose").get())
            add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            add("implementation", libs.findLibrary("androidx-paging-compose").get())
        }
    }

    fun Project.configureNetworkingDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("api", libs.findLibrary("retrofit").get())
            add("api", libs.findLibrary("retrofit-kotlinx-serialization").get())
            add("implementation", libs.findLibrary("okhttp-logging").get())
            add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
        }
    }

    fun Project.configureImageDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("coil").get())
            add("implementation", libs.findLibrary("coil-compose").get())
        }
    }

    fun Project.configurePersistenceDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("androidx-datastore-preferences").get())
        }
    }

    fun Project.configurePagingDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("androidx-paging-runtime").get())
        }
    }

    fun Project.configureLoggingDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("timber").get())
        }
    }

    fun Project.configureNavigationDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("androidx-navigation-compose").get())
        }
    }

    fun Project.configureAppDependencies() {
        val libs = getVersionCatalog()
        dependencies {
            add("implementation", project(":core"))
            add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
        }
    }
}