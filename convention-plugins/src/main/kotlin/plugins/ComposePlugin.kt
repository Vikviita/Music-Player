package plugins

import android
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import plugins

class ComposePlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            plugins {
                apply(libs.plugins.kotlin.compose)
            }
            android {
                buildFeatures {
                    compose = true
                }
            }
            dependencies {
                add("implementation",libs.androidx.activity.compose)
                add("implementation",libs.androidx.viewmodel.compose)
                add("implementation",libs.androidx.compose.bom)
                add("implementation",libs.androidx.ui.tooling.preview)
                add("debugImplementation",libs.androidx.ui.tooling)
                add("implementation",libs.androidx.material3)
            }
        }

    }
}