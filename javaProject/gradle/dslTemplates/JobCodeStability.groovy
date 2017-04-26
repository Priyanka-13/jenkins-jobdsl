job("DirectLoginCodeStabilityDsl") {
  description('Code Stability for API')
  
   logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  
  parameters {
        activeChoiceParam('BRANCH') {
             
            groovyScript {
            script('groovy script')
            }
       choiceType('SINGLE_SELECT')
        }
   
     }
  scm {
     git {
      remote {
        url("git@github.com:OpsTree/Spring3HibernateAppWithGradle.git")
      }
       branch('${BRANCH}')
     }
   }
  
  steps {
        gradle {

           useWrapper false
            gradleName ( 'gradle-3.5' )
            tasks ( "war" )
            buildFile ( 'build.gradle' )

        }
    }
  
  steps {
        shell(

          'basePath=${JENKINS_HOME}/BUILDS \n' +
          'repoName="web" \n' +
          'branchName=$BRANCH \n' +
          'mkdir -p ${basePath}/${repoName}/${branchName} \n' +
          '#cp $WORKSPACE/build/lib/*war  ${basePath}/${repoName}/${branchName}/ \n' +
          'touch ${basePath}/${repoName}/${branchName}/DirectLogin.war \n' 
       )

    }
}
