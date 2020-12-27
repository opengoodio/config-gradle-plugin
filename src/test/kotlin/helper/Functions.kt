package helper

import io.opengood.gradle.ConfigPlugin
import io.opengood.gradle.constant.Directories
import io.opengood.gradle.enumeration.BuildGradleType
import io.opengood.gradle.enumeration.LanguageType
import io.opengood.gradle.enumeration.ProjectType
import io.opengood.gradle.extension.OpenGoodExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.internal.artifacts.dsl.DefaultRepositoryHandler
import org.gradle.api.internal.plugins.DslObject
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.MavenRepositoryHandlerConvention
import org.gradle.api.publication.maven.internal.deployer.DefaultGroovyMavenDeployer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.testfixtures.ProjectBuilder
import java.nio.file.Files
import java.nio.file.Path

private fun <T : Any> T.accessField(fieldName: String): Any? =
    javaClass.getDeclaredField(fieldName).let { field ->
        field.isAccessible = true
        return@let field.get(this)
    }

internal fun createProject(
    languageType: LanguageType,
    name: String = "test",
    group: String = "org.example",
    version: String = "1.0.0-SNAPSHOT",
    projectType: ProjectType = ProjectType.APP,
    features: Int = defaultFeatures,
    multipleFrameworks: Int = defaultMultipleFrameworks
): Project {
    return ProjectBuilder.builder()
        .withName(name)
        .withProjectDir(createProjectDir(languageType).toFile())
        .build().also { project ->
            project.group = group
            project.version = version
            with(project) {
                pluginManager.apply(ConfigPlugin.PLUGIN_ID)
                extensions.configure(OpenGoodExtension::class.java) { ext ->
                    ext.main.projectType = projectType
                    ext.features = getFeatures(project, features)
                    ext.test.multipleFrameworks = getMultipleFrameworks(project, multipleFrameworks)
                }
            }
            (project as ProjectInternal).evaluate()
        }
}

internal fun createProjectDir(languageType: LanguageType): Path {
    val projectDir = Files.createTempDirectory("")
    when (languageType) {
        LanguageType.GROOVY -> projectDir.resolve(Directories.GROOVY_SRC).toFile().mkdirs()
        LanguageType.JAVA -> projectDir.resolve(Directories.JAVA_SRC).toFile().mkdirs()
        LanguageType.KOTLIN -> projectDir.resolve(Directories.KOTLIN_SRC).toFile().mkdirs()
    }
    return projectDir
}

internal inline fun <reified T : Any> getArtifact(
    project: Project,
    configuration: String,
    name: String,
    vararg parts: String
): T =
    project.configurations.getByName(configuration).artifacts
        .filter { it is T }
        .first { it.toString().endsWith("${project.name}:${parts.joinToString(":")}:$name") }
        .let { it as T }

internal fun getBuildGradleFile(languageType: LanguageType): String =
    when (languageType) {
        LanguageType.KOTLIN -> BuildGradleType.KOTLIN.toString()
        else -> BuildGradleType.GROOVY.toString()
    }

internal inline fun <reified T : Any> getConvention(project: Project): T =
    project.convention.getPlugin(T::class.java)

internal fun getDependency(project: Project, configuration: String, name: String): Dependency? =
    project.configurations.getByName(configuration).dependencies
        .firstOrNull { it == project.dependencies.create(name) }

internal fun getMavenDeployer(repositories: RepositoryHandler): DefaultGroovyMavenDeployer =
    DslObject(repositories).convention.getPlugin(MavenRepositoryHandlerConvention::class.java)
        .let { it.accessField("container") as DefaultRepositoryHandler }
        .let { it.getByName("mavenDeployer") as DefaultGroovyMavenDeployer }

internal fun getMavenPublication(extension: PublishingExtension, name: String): MavenPublication =
    extension.publications.getByName(name) as MavenPublication

internal fun getMavenRepository(extension: PublishingExtension, name: String): MavenArtifactRepository =
    extension.repositories.getByName(name) as MavenArtifactRepository

internal inline fun <reified T : Plugin<*>> getPlugin(project: Project): T =
    project.plugins.getPlugin(T::class.java)

internal fun getPlugin(project: Project, id: String): Plugin<Any> =
    project.plugins.getPlugin(id)

internal fun getRepository(project: Project, name: String): ArtifactRepository =
    project.repositories.getByName(name)

internal fun getTaskByName(project: Project, name: String): Task =
    project.tasks.getByName(name)

internal inline fun <reified T : Task> getTaskByType(project: Project): T =
    project.tasks.withType(T::class.java).first()

 internal inline fun <reified T : Task> getTaskByTypeAndName(project: Project, name: String): T =
    project.tasks.withType(T::class.java).getByName(name)