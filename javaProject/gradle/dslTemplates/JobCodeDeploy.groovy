job("CodeDeploy") {
  description('Code deploy for DirectLogin')
  parameters {
        activeChoiceParam('REPOSITORY') {
             
            groovyScript {
            fallbackScript('return["Script Error"]')
            script('evaluate(new File("/var/lib/jenkins/scripts/getRepoList.groovy"))')
            }
	    description('The list of repository get evaluated automatically')
            choiceType('SINGLE_SELECT')
        }
    
   activeChoiceReactiveParam('BRANCH') {
            
            choiceType('SINGLE_SELECT')
            groovyScript {
		fallbackScript('return["Script Error"]')
                script('evaluate(new File("/var/lib/jenkins/scripts/getBranchForRepo.groovy"))')
               
            }
	    description('The list of brncahes as per selection of repository get evaluated automatically')
            referencedParameter('REPOSITORY')
            
        }
    
    activeChoiceReactiveParam('ARTIFACT') {
            
            choiceType('SINGLE_SELECT')
            groovyScript {
		fallbackScript('return["Script Error"]')
                script('evaluate(new File("/var/lib/jenkins/scripts/getArtifactList.groovy"))')
               
            }
	    description('The list of artifact got evaluated automatically on basis of repo and branch')
            referencedParameter('REPOSITORY,BRANCH')
            
        }
    
    
       choiceParam('Environment', ['Dev', 'QA', 'Production'], 'Target environment to deploy artifact')
    }
  
  steps {
        steps {
        shell('sudo ansible-playbook -i /etc/ansible/roles/code/hosts /etc/ansible/roles/code/site.yml')
      }
    }
}
