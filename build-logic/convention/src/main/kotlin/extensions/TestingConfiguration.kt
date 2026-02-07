package extensions

import configuration.DependencyConfiguration.configureTestDependencies
import org.gradle.api.Project

fun Project.configureTesting() {
    configureTestDependencies()
}