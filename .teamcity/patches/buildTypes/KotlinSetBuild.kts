package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'KotlinSetBuild'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("KotlinSetBuild")
    name = "Kotlin_Set_Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = "pwd"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
}))

