plugins {
    alias(libs.plugins.themoviedb.app)
}

// All Android configuration is now handled by the themoviedb.android.app plugin
// No need for android block - it's all centralized in the convention plugin

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(project(":feature:genre"))
    implementation(project(":feature:movies"))
}