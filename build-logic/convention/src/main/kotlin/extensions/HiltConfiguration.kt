package extensions

import configuration.DependencyConfiguration.configureHiltDependencies
import org.gradle.api.Project

fun Project.configureHilt() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
        apply("com.google.dagger.hilt.android")
    }
    
    configureHiltDependencies()
}