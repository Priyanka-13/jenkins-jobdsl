def thr = Thread.currentThread()
def build = thr.executable
// get build parameters
def buildVariablesMap = build.buildVariables
String projectname = buildVariablesMap?.ProjectName
String appurl = buildVariablesMap?.AppURL
String gitbranch = buildVariablesMap?.GitBranch
String gradletask = buildVariablesMap?.GradleTask


//println appurl

/* get all environment variables for the build
def buildEnvVarsMap = build.envVars
String jobName = buildEnvVarsMap?.JOB_NAME
println jobName
*/

 job("${projectname}CodeStability") {
  description('Code Stability for API')
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  scm {
     git {
      remote {
        url("$appurl")
      }
       branch("$gitbranch")
     }
   }
  steps {
        gradle {

           useWrapper false
            gradleName ( 'gradle-3.5' )
            tasks ( "$gradletask" )
            buildFile ( 'build.gradle' )

        }
    }
}
