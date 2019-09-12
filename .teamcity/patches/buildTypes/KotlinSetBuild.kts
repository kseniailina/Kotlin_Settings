package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.CommitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'KotlinSetBuild'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("KotlinSetBuild")) {
    params {
        add {
            param("system.commit.status.publisher.username", "kseniailina")
        }
    }

    features {
        val feature1 = find<CommitStatusPublisher> {
            commitStatusPublisher {
                vcsRootExtId = "KotlinSet_KotlinSettings_HttpsGithubComKseniailinaKotlinSettingsGit1"
                publisher = github {
                    githubUrl = "https://api.github.com"
                    authType = password {
                        userName = "%system.commit.status.publisher.username%"
                        password = "%system.commit.status.publisher.password%"
                    }
                }
            }
        }
        feature1.apply {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = password {
                    userName = "%system.commit.status.publisher.username%"
                    password = "credentialsJSON:f88fa568-e1e9-460b-87cd-4e738cf7caea"
                }
            }
        }
    }
}
