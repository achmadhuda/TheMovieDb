package configuration

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import kotlin.apply
import kotlin.io.use

abstract class LocalPropsService : BuildService<BuildServiceParameters.None> {
    val properties: Properties by lazy {
        Properties().apply {
            val file = File("local.properties")
            if (file.exists()) {
                FileInputStream(file).use { load(it) }
            }
        }
    }

    fun getProperty(key: String, defaultValue: String = ""): String {
        return properties.getProperty(key, defaultValue)
    }
}