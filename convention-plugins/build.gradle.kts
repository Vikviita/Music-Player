import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    compileOnly(libs.gradletools.kotlin)
    compileOnly(libs.gradletools.android)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}


gradlePlugin{
    plugins {
        register("common"){
            id = "convention.common.plugin"
            implementationClass = "plugins.CommonPlugin"
        }
        register("compose"){
            id="convention.compose.plugin"
            implementationClass = "plugins.ComposePlugin"
        }
        register("di"){
            id ="convention.di.plugin"
            implementationClass = "plugins.DiPlugin"
        }
        register("serialization"){
            id="convention.serialization.plugin"
            implementationClass = "plugins.DiPlugin"
        }
    }
}