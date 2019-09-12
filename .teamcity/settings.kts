import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

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

        features {
            commitStatusPublisher {
                vcsRootExtId = "${MyVSCRoot.id}"
                publisher = bitbucketServer {
                    url = "http://tcqa-bitbucket-server:7990"
                    userName = "%system.commit.status.publisher.username%"
                    password = "%system.commit.status.publisher.password%"
                }
            }
        }
        dependencies {
            dependency(AbsoluteId("SecRepos_Build")) {
                snapshot {
                    onDependencyFailure = FailureAction.FAIL_TO_START
                }
            }
        }
    })

    object MyVSCRoot : GitVcsRoot({
        id("HttpAdminTcqaBitbucketServer7990scmKsepKseproject1git")
        name = "http://admin@tcqa-bitbucket-server:7990/scm/ksep/kseproject1.git"
        url = "http://admin@tcqa-bitbucket-server:7990/scm/ksep/kseproject1.git"
        branchSpec = """
        +:refs/heads/(*)
        -:refs/heads/release/4.0
    """.trimIndent()
        authMethod = password {
            userName = "%system.bitbuck.username%"
            password = "%system.bitbuck.password%"
        }
        serverSideAutoCRLF = true
    })

