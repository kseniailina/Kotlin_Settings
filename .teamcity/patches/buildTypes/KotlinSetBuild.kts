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
            param("system.commit.status.publisher.username", "admin")
        }
    }

    features {
        val feature1 = find<CommitStatusPublisher> {
            commitStatusPublisher {
                vcsRootExtId = "DSLTest_HttpAdminTcqaBitbucketServer7990scmKsepKseproject1git"
                publisher = bitbucketServer {
                    url = "http://tcqa-bitbucket-server:7990"
                    userName = "%system.commit.status.publisher.username%"
                    password = "%system.commit.status.publisher.password%"
                }
            }
        }
        feature1.apply {
            publisher = bitbucketServer {
                url = "http://tcqa-bitbucket-server:7990"
                userName = "%system.commit.status.publisher.username%"
                password = "credentialsJSON:88c7aa51-26b1-467b-b9a6-c8635b8c40f0"
            }
        }
    }
}
