plugins {
    alias(libs.plugins.convention.common)
    alias(libs.plugins.convention.di)
}
dependencies {
    implementation(project(":domain"))
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.retrofitGsonConverter)
}