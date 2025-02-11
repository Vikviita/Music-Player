plugins {
    alias(libs.plugins.convention.common)
    alias(libs.plugins.convention.di)
    alias(libs.plugins.convention.serialization)
}
dependencies {
    implementation(project(":domain"))
    implementation(libs.retrofit)
}