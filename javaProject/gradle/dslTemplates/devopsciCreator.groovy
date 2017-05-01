job("Devops-CI-creator") {
  parameters {
        stringParam('ProjectName', '', 'Name of the Project')
        stringParam('AppURL')
        stringParam('GitBranch')
  
    choiceParam('GradleTask', ['jar', 'war', ])
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
