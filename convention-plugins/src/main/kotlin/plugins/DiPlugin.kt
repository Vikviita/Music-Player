package plugins

import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import plugins

class DiPlugin:Plugin<Project> {
    override fun apply(target: Project){
        with(target){
            plugins {
                apply(libs.plugins.kotlin.kapt)
            }
            dependencies {
                add("implementation",libs.dagger)
                add("kapt",libs.dagger.compiler)
            }
        }
    }
}