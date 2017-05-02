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
               description('Repository name on bitbucket ex: \'web\'')
          }
            activeChoiceReactiveParam('BRANCH') {

               choiceType('SINGLE_SELECT')
               groovyScript {
                  fallbackScript('return["Script Error"]')
                  script('evaluate(new File("/var/lib/jenkins/scripts/getBranchForRepo.groovy"))')
               }
	       description('This get evaluated automatically. Shows the list of available braches in your project repository.')
               referencedParameter('REPOSITORY')
            }
 }
  scm {
     git {
      remote {
        url("${appurl}")
      }
       branch("${gitbranch}")
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
