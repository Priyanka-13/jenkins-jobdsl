job("CodeDeploy") {
  description('Code deploy for DirectLogin')
  parameters {
        activeChoiceParam('REPOSITORY') {
             
            groovyScript {
            fallbackScript('return["Script Error"]')
            script('evaluate(new File("/var/lib/jenkins/scripts/getRepoList.groovy"))')
            }
       choiceType('SINGLE_SELECT')
        }
    
   activeChoiceReactiveParam('BRANCH') {
            
            choiceType('SINGLE_SELECT')
            groovyScript {
		fallbackScript('return["Script Error"]')
                script('evaluate(new File("/var/lib/jenkins/scripts/getBranchForRepo.groovy"))')
               
            }
            referencedParameter('REPOSITORY')
            
        }
    
    activeChoiceReactiveParam('ARTIFACT') {
            
            choiceType('SINGLE_SELECT')
            groovyScript {
		fallbackScript('return["Script Error"]')
                script('evaluate(new File("/var/lib/jenkins/scripts/getArtifactList.groovy"))')
               
            }
            referencedParameter('REPOSITORY,BRANCH')
            
        }
    
    
       choiceParam('Environment', ['Dev', 'QA', 'Production'])
    }
  
  steps {
        steps {
        shell('sudo ansible-playbook -i /etc/ansible/roles/code/hosts /etc/ansible/roles/code/site.yml')
      }
    }
}
