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
