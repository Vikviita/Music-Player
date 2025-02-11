package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectConfig {
    const val nameSpace = "com.vikvita.musicplayer"
    const val compileSdk = 35
    const val minSdk = 24
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0.0"
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
    val kotlinJvmTarget = JvmTarget.JVM_17
}