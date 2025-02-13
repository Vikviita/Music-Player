import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency

internal val Project.libs
    get() = the<LibrariesForLibs>()

internal fun Provider<PluginDependency>.getPluginId():String = this.get().pluginId


private val Project.libraryExtension: LibraryExtension
    get() = extensions.findByType(LibraryExtension::class)
        ?: error(
            "\"Project.androidExtension\" value may be called only from android application" +
                    " or android library gradle script"
        )

internal fun Project.android(block: LibraryExtension.() -> Unit): Unit = block(libraryExtension)
internal fun Project.plugins(block: PluginManager.() -> Unit):Unit = block(pluginManager)