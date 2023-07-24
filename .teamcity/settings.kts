import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubIssues
import jetbrains.buildServer.configs.kotlin.triggers.vcs

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

version = "2023.05"

project {

    buildType(Issues_Build)

    features {
        githubIssues {
            id = "PROJECT_EXT_13"
            displayName = "kilina0/ahooks"
            repositoryURL = "https://github.com/kilina0/ahooks"
            authType = storedToken {
                tokenId = "tc_token_id:CID_e5a766b029467a6b5f712451464d33e4:-1:3fa9bef2-0b41-40d9-a1d7-2608c3ff288c"
            }
        }
        githubIssues {
            id = "PROJECT_EXT_14"
            displayName = "manual"
            repositoryURL = "https://github.com/kilina0/issues"
            param("tokenId", "")
        }
        githubAppConnection {
            id = "PROJECT_EXT_15"
            displayName = "GitHub App Personal"
            appId = "360747"
            clientId = "Iv1.a29ddb8955f61941"
            clientSecret = "credentialsJSON:7dc6bb9d-aba3-499b-8af1-5dba0786a5e8"
            privateKey = "credentialsJSON:4d294c30-7652-47c5-aa36-e3e72fd7905d"
            ownerUrl = "https://github.com/kilina0"
        }
    }

    subProject(Issues_Subpro)
}

object Issues_Build : BuildType({
    id("Build")
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})


object Issues_Subpro : Project({
    id("Subpro")
    name = "Subpro"

    features {
        githubIssues {
            id = "PROJECT_EXT_19"
            displayName = "kilina0/ahooks"
            repositoryURL = "https://github.com/kilina0/ahooks"
            authType = storedToken {
                tokenId = "tc_token_id:CID_e5a766b029467a6b5f712451464d33e4:-1:68473e2c-8aa7-4463-a007-b6d5d844b246"
            }
        }
    }
})
