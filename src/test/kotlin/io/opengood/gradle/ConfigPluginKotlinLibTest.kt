package io.opengood.gradle

import helper.createProject
import io.kotest.core.spec.style.FunSpec
import io.opengood.gradle.enumeration.LanguageType
import io.opengood.gradle.enumeration.ProjectType
import spec.addKotestDependenciesTest
import spec.addKotlinDependenciesTest
import spec.addMockkDependenciesTest
import spec.addRepositoriesTest
import spec.addSpringDependenciesTest
import spec.applyCommonPluginsTest
import spec.applyJavaPluginTest
import spec.applyKotlinPluginsTest
import spec.applyKotlinSpringPluginsTest
import spec.applyLibraryPluginTest
import spec.applyPluginTest
import spec.applySpringPluginsTest
import spec.configureAfterReleaseBuildTaskTest
import spec.configureBootJarTaskTest
import spec.configureConventionsTest
import spec.configureDependencyUpdatesTaskTest
import spec.configureGradleWrapperTaskTest
import spec.configureJarArtifactTest
import spec.configureJarSigningTest
import spec.configureJarTaskTest
import spec.configureJavaCompileTaskTest
import spec.configureJavadocJarArtifactTest
import spec.configureJavadocJarSigningTest
import spec.configureKotlinCompileTaskTest
import spec.configurePublishingExtensionTest
import spec.configureReleaseExtensionTest
import spec.configureSigningExtensionTest
import spec.configureSourcesJarArtifactTest
import spec.configureSourcesJarSigningTest
import spec.configureTestTaskTest
import spec.configureUploadArchivesTaskTest
import spec.createExtensionTest
import spec.doNotAddAssertjDependenciesTest
import spec.doNotAddGroovyDependenciesTest
import spec.doNotAddJunitDependenciesTest
import spec.doNotAddLombokDependenciesTest
import spec.doNotAddMockitoDependenciesTest
import spec.doNotApplyGroovyPluginTest
import spec.doNotApplyLombokPluginTest

class ConfigPluginKotlinLibTest : FunSpec({

    val project = createProject(
        languageType = LanguageType.KOTLIN,
        projectType = ProjectType.LIB
    )

    include(applyPluginTest(project))

    include(createExtensionTest(project, ProjectType.LIB))

    include(applyKotlinPluginsTest(project))
    include(applyJavaPluginTest(project))
    include(applyCommonPluginsTest(project))
    include(applyLibraryPluginTest(project))
    include(applySpringPluginsTest(project))
    include(applyKotlinSpringPluginsTest(project))
    include(doNotApplyGroovyPluginTest(project))
    include(doNotApplyLombokPluginTest(project))

    include(configureConventionsTest(project))

    include(addRepositoriesTest(project))

    include(addKotlinDependenciesTest(project))
    include(addSpringDependenciesTest(project))
    include(addKotestDependenciesTest(project))
    include(addMockkDependenciesTest(project))
    include(doNotAddGroovyDependenciesTest(project))
    include(doNotAddLombokDependenciesTest(project))
    include(doNotAddJunitDependenciesTest(project))
    include(doNotAddAssertjDependenciesTest(project))
    include(doNotAddMockitoDependenciesTest(project))

    include(configureGradleWrapperTaskTest(project))
    include(configureKotlinCompileTaskTest(project))
    include(configureJavaCompileTaskTest(project))
    include(configureDependencyUpdatesTaskTest(project))
    include(configureTestTaskTest(project))
    include(configureJarTaskTest(project, isEnabled = true))
    include(configureBootJarTaskTest(project, isEnabled = false))
    include(configureAfterReleaseBuildTaskTest(project))
    include(configureUploadArchivesTaskTest(project))

    include(configureSourcesJarArtifactTest(project))
    include(configureJavadocJarArtifactTest(project))
    include(configureJarArtifactTest(project))

    include(configureReleaseExtensionTest(project))
    include(configurePublishingExtensionTest(project))
    include(configureSigningExtensionTest(project))

    include(configureSourcesJarSigningTest(project))
    include(configureJavadocJarSigningTest(project))
    include(configureJarSigningTest(project))
})
