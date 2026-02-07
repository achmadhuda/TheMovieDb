package configuration

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import configuration.ProjectConfiguration.BuildTypes
import configuration.ProjectConfiguration.Flavors
import configuration.ProjectConfiguration.ProGuard
import org.gradle.api.Project

object BuildTypeConfiguration {

    fun ApplicationExtension.configureApplicationBuildTypes(props: LocalPropsService, target: Project) {
        configureBuildTypes()
        configureFlavors(props)
    }



    fun LibraryExtension.configureLibraryBuildTypes() {
        buildTypes {
            debug {
                isMinifyEnabled = BuildTypes.Debug.IS_MINIFY_ENABLED
            }

            release {
                isMinifyEnabled = BuildTypes.Release.IS_MINIFY_ENABLED
                proguardFiles(
                    getDefaultProguardFile(ProGuard.ANDROID_OPTIMIZE),
                    ProGuard.RULES_FILE
                )
            }
        }
    }

    private fun ApplicationExtension.configureBuildTypes() {
        buildTypes {
            debug {
                isDebuggable = BuildTypes.Debug.IS_DEBUGGABLE
                isMinifyEnabled = BuildTypes.Debug.IS_MINIFY_ENABLED
                versionNameSuffix = BuildTypes.Debug.VERSION_NAME_SUFFIX

                buildConfigField("boolean", "DEBUG_MODE", "true")
            }

            release {
                isMinifyEnabled = BuildTypes.Release.IS_MINIFY_ENABLED
                isShrinkResources = BuildTypes.Release.IS_SHRINK_RESOURCES

                buildConfigField("boolean", "DEBUG_MODE", "false")

                proguardFiles(
                    getDefaultProguardFile(ProGuard.ANDROID_OPTIMIZE),
                    ProGuard.RULES_FILE
                )
            }
        }
    }

    private fun ApplicationExtension.configureFlavors(
        props: LocalPropsService,
    ) {
        flavorDimensions += Flavors.DIMENSION

        productFlavors {
            create(Flavors.Production.NAME) {
                dimension = Flavors.DIMENSION
                buildConfigField("String", "ENVIRONMENT", "\"${Flavors.Production.NAME}\"")
                buildConfigField("String", "BASE_URL", props.getProperty("BASE_URL"))
                buildConfigField("String", "API_KEY", props.getProperty("API_KEY"))
            }

            create(Flavors.Staging.NAME) {
                dimension = Flavors.DIMENSION
                applicationIdSuffix = Flavors.Staging.APPLICATION_ID_SUFFIX
                versionNameSuffix = Flavors.Staging.VERSION_NAME_SUFFIX
                buildConfigField("String", "ENVIRONMENT", "\"${Flavors.Staging.NAME}\"")
                buildConfigField("String", "BASE_URL", props.getProperty("BASE_URL"))
                buildConfigField("String", "API_KEY", props.getProperty("API_KEY"))
            }
        }
    }

    fun ApplicationExtension.configureReleaseSigning(
        project: Project,
        storeFilePath: String,
        storePassword: String,
        keyAlias: String,
        keyPassword: String
    ) {
        signingConfigs {
            create("release") {
                storeFile = project.file(storeFilePath)
                this.storePassword = storePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            }
        }

        buildTypes {
            release {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    fun ApplicationExtension.configureBuildConfigFields() {
        defaultConfig {
            buildConfigField("String", "VERSION_NAME", "\"${ProjectConfiguration.VERSION_NAME}\"")
            buildConfigField("int", "VERSION_CODE", "${ProjectConfiguration.VERSION_CODE}")
            buildConfigField("String", "APPLICATION_ID", "\"${ProjectConfiguration.APPLICATION_ID}\"")
        }
    }
}