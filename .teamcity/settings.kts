import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {
    vcsRoot(MyVSCRoot)
    buildType(Build)
}

    object Build : BuildType({
        id("KotlinSetBuild")
        name = "Kotlin_Set_Build"
        allowExternalStatus = true
        buildNumberPattern = "unknown-%build.counter%"

        vcs {
            root(MyVSCRoot)
            checkoutMode = CheckoutMode.ON_AGENT
            cleanCheckout = true
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
    })

    object MyVSCRoot : GitVcsRoot({
        id("KotlinSettings_HttpsGithubComKseniailinaKotlinSettingsGit1")
        name = "https://github.com/kseniailina/Kotlin_Settings.git"
        url = "https://github.com/kseniailina/Kotlin_Settings.git"
        branchSpec = """
        +:refs/heads/(*)
        -:refs/heads/release/4.0
    """.trimIndent()
        serverSideAutoCRLF = true
    })

