package plugins

import android
import config.ProjectConfig
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import plugins
import java.io.File

internal class CommonPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            plugins{
                apply(libs.plugins.android.library)
                apply(libs.plugins.kotlin.android)
            }
           android {
               val parentDirName = File(project.projectDir.absolutePath).name
               namespace = ProjectConfig.nameSpace + parentDirName
               compileSdk = ProjectConfig.compileSdk
               defaultConfig {
                   minSdk = ProjectConfig.minSdk
                   consumerProguardFiles("consumer-rules.pro")
               }
               lint.targetSdk = ProjectConfig.targetSdk
               compileOptions.apply {
                   sourceCompatibility = ProjectConfig.javaVersion
                   targetCompatibility = ProjectConfig.javaVersion
               }
               tasks.withType<KotlinJvmCompile>().configureEach {
                   compilerOptions {
                       jvmTarget.set(ProjectConfig.kotlinJvmTarget)
                   }
               }
           }
        }

    }
}