job("Devops-CI-creator") {
  parameters {
        stringParam('ProjectName', '', 'Name of the Project')
        stringParam('AppURL', '', 'Application url of your codebase repository.')
        stringParam('GitBranch', '', 'Branch to build')
  
    choiceParam('GradleTask', ['jar', 'war', ], 'Gradle task to perform when build triggered')
    }
         
   
  scm {
     git {
      remote {
        url("https://github.com/OpsTree/jenkins-jobdsl.git")
      }
       branch("*/master")
     }
   }
        
    steps {
        groovyScriptFile('javaProject/gradle/dslGenerator.groovy') {
            groovyInstallation('Default')
            
        }
    }

 steps {
      dsl  {
            external('javaProject/gradle/dslfiles/Job*.groovy')
            removeAction('IGNORE')
            
            
        }
    }   
}
