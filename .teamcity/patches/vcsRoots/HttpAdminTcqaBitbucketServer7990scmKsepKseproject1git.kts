package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the vcsRoot with id = 'HttpAdminTcqaBitbucketServer7990scmKsepKseproject1git'
accordingly, and delete the patch script.
*/
changeVcsRoot(RelativeId("HttpAdminTcqaBitbucketServer7990scmKsepKseproject1git")) {
    val expected = GitVcsRoot({
        id("HttpAdminTcqaBitbucketServer7990scmKsepKseproject1git")
        name = "http://admin@tcqa-bitbucket-server:7990/scm/ksep/kseproject1.git"
        url = "http://admin@tcqa-bitbucket-server:7990/scm/ksep/kseproject1.git"
        branchSpec = """
            +:refs/heads/(*)
            -:refs/heads/release/4.0
        """.trimIndent()
        serverSideAutoCRLF = true
        authMethod = password {
            userName = "admin"
            password = "admin"
        }
    })

    check(this == expected) {
        "Unexpected VCS root settings"
    }

    (this as GitVcsRoot).apply {
        authMethod = password {
            userName = "admin"
            password = "credentialsJSON:d36b965b-5b1c-4e1e-970d-f3b681f6ad39"
        }
    }

}