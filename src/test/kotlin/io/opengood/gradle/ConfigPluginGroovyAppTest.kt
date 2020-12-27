package io.opengood.gradle

import helper.createProject
import io.kotest.core.spec.style.FunSpec
import io.opengood.gradle.enumeration.LanguageType
import io.opengood.gradle.enumeration.ProjectType
import spec.addAssertjDependenciesTest
import spec.addGroovyDependenciesTest
import spec.addJunitDependenciesTest
import spec.addMockitoDependenciesTest
import spec.addRepositoriesTest
import spec.addSpringDependenciesTest
import spec.applyCommonPluginsTest
import spec.applyGroovyPluginTest
import spec.applyJavaPluginTest
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
import spec.configureProcessResourcesTaskTest
import spec.configurePublishingExtensionTest
import spec.configureReleaseExtensionTest
import spec.configureSigningExtensionTest
import spec.configureSpringBootExtensionTest
import spec.configureTestTaskTest
import spec.configureUploadArchivesTaskTest
import spec.createExtensionTest
import spec.doNotAddKotestDependenciesTest
import spec.doNotAddKotlinDependenciesTest
import spec.doNotAddLombokDependenciesTest
import spec.doNotAddMockkDependenciesTest
import spec.doNotApplyKotlinPluginsTest
import spec.doNotApplyKotlinSpringPluginsTest
import spec.doNotApplyLibraryPluginTest
import spec.doNotApplyLombokPluginTest

class ConfigPluginGroovyAppTest : FunSpec({

    val project = createProject(
        languageType = LanguageType.GROOVY,
        projectType = ProjectType.APP
    )

    include(applyPluginTest(project))

    include(createExtensionTest(project, ProjectType.APP))

    include(applyGroovyPluginTest(project))
    include(applyJavaPluginTest(project))
    include(applyCommonPluginsTest(project))
    include(applySpringPluginsTest(project))
    include(doNotApplyKotlinPluginsTest(project))
    include(doNotApplyLibraryPluginTest(project))
    include(doNotApplyKotlinSpringPluginsTest(project))
    include(doNotApplyLombokPluginTest(project))

    include(configureConventionsTest(project))

    include(addRepositoriesTest(project))

    include(addGroovyDependenciesTest(project))
    include(addSpringDependenciesTest(project))
    include(addJunitDependenciesTest(project))
    include(addAssertjDependenciesTest(project))
    include(addMockitoDependenciesTest(project))
    include(doNotAddKotlinDependenciesTest(project))
    include(doNotAddLombokDependenciesTest(project))
    include(doNotAddKotestDependenciesTest(project))
    include(doNotAddMockkDependenciesTest(project))

    include(configureGradleWrapperTaskTest(project))
    include(configureProcessResourcesTaskTest(project))
    include(configureJavaCompileTaskTest(project))
    include(configureDependencyUpdatesTaskTest(project))
    include(configureTestTaskTest(project))
    include(configureJarTaskTest(project, isEnabled = false))
    include(configureBootJarTaskTest(project, isEnabled = true))
    include(configureAfterReleaseBuildTaskTest(project))
    include(configureUploadArchivesTaskTest(project))

    include(configureJarArtifactTest(project))

    include(configureSpringBootExtensionTest(project))
    include(configureReleaseExtensionTest(project))
    include(configurePublishingExtensionTest(project))
    include(configureSigningExtensionTest(project))

    include(configureJarSigningTest(project))
})
