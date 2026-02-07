package configuration

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectConfiguration {
    
    const val COMPILE_SDK = 36
    const val TARGET_SDK = 36
    const val MIN_SDK = 26
    
    val JAVA_VERSION = JavaVersion.VERSION_11
    val JVM_TARGET = JvmTarget.JVM_11
    const val JVM_TARGET_STRING = "11"
    
    const val APPLICATION_ID = "com.themoviedb"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    
    object Namespace {
        const val APP = "com.themoviedb"
        const val CORE = "$APP.core"
        
        object Feature {
            private const val BASE = "$APP.feature"
            const val GENRE = "$BASE.genre"
            const val MOVIE = "$BASE.movie"
        }
    }
    
    object Flavors {
        const val DIMENSION = "environment"
        
        object Production {
            const val NAME = "production"
            const val APPLICATION_ID_SUFFIX = ""
            const val VERSION_NAME_SUFFIX = ""
        }
        
        object Staging {
            const val NAME = "staging"
            const val APPLICATION_ID_SUFFIX = ".staging"
            const val VERSION_NAME_SUFFIX = "-staging"
        }
    }
    
    object BuildTypes {
        object Debug {
            const val NAME = "debug"
            const val IS_DEBUGGABLE = true
            const val IS_MINIFY_ENABLED = false
            const val APPLICATION_ID_SUFFIX = ".debug"
            const val VERSION_NAME_SUFFIX = "-debug"
        }
        
        object Release {
            const val NAME = "release"
            const val IS_DEBUGGABLE = false
            const val IS_MINIFY_ENABLED = false
            const val IS_SHRINK_RESOURCES = false
        }
    }
    
    object CompilerOptions {
        val FREE_COMPILER_ARGS = listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.media3.common.util.UnstableApi",
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        )
    }
    
    object ProGuard {
        const val ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
        const val RULES_FILE = "proguard-rules.pro"
        const val CONSUMER_RULES = "consumer-rules.pro"
    }
    
    object Packaging {
        val EXCLUDED_RESOURCES = setOf(
            "/META-INF/{AL2.0,LGPL2.1}",
            "/META-INF/LICENSE",
            "/META-INF/LICENSE.txt",
            "/META-INF/license.txt",
            "/META-INF/NOTICE",
            "/META-INF/NOTICE.txt",
            "/META-INF/notice.txt",
            "/META-INF/ASL2.0",
            "/META-INF/*.kotlin_module"
        )
    }
}