plugins {
    alias(libs.plugins.convention.common)
    alias(libs.plugins.convention.compose)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":uikit"))
    implementation(libs.compose.coil)
    implementation(libs.compose.coil.okhttp)
}