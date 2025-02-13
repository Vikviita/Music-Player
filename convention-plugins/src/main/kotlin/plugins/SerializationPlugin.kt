package plugins


import getPluginId
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import plugins

class SerializationPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            plugins {
                apply(libs.plugins.kotlin.serialization.getPluginId())
            }
            dependencies {
             add("implementation",libs.kotlin.serialization)
            }
        }
    }
}