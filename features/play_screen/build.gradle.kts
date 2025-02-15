plugins {
    alias(libs.plugins.convention.common)
    alias(libs.plugins.convention.compose)
}
dependencies {
    implementation(libs.androidx.media)
    implementation(libs.androidx.media.exoplayer)
    implementation(libs.compose.coil)

    implementation(project(":domain"))
    implementation(project(":uikit"))
}