plugins {
    alias(libs.plugins.convention.common)
    alias(libs.plugins.convention.compose)
}
dependencies {
    implementation(libs.androidx.media)
    implementation(project(":domain"))
    implementation(project(":uikit"))
}