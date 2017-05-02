job("${projectname}CodeStability") {
  description('Code Stability for API')
  
   logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  
  parameters {

	    wHideParameterDefinition {
               name('REPOSITORY')
               defaultValue("${projectname}")
               description('repository name')
          }
            activeChoiceReactiveParam('BRANCH') {

            choiceType('SINGLE_SELECT')
            groovyScript {
                fallbackScript('return["Script Error"]')
                script('evaluate(new File("/var/lib/jenkins/scripts/getBranchForRepo.groovy"))')

            }
            referencedParameter('REPOSITORY')

        }
     }
  scm {
     git {
      remote {
        url("${appurl}")
      }
       branch("\${gitbranch}")
     }
   }
  
  steps {
        gradle {

           useWrapper false
            gradleName ( 'gradle-3.5' )
            tasks ( "${gradletask}" )
            buildFile ( 'build.gradle' )

        }
    }
  
  steps {
        shell(

          'basePath=\${JENKINS_HOME}/BUILDS \\n' +
          'repoName="web" \\n' +
          'branchName=\$BRANCH \\n' +
          'mkdir -p \${basePath}/\${repoName}/\${branchName} \\n' +
          '#cp \$WORKSPACE/build/lib/*war  \${basePath}/\${repoName}/\${branchName}/ \\n' +
          'touch \${basePath}/\${repoName}/\${branchName}/DirectLogin.war \\n' 
       )

    }
}
