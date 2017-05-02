job("DevopsDeploySSHKey") {
  description('Deploy key on Bitbucket into specific requirement.')
  
   logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  
  parameters {

            activeChoiceReactiveParam('RepositoryName') {
	    	description('List of repositories. This will get evaluated automatically.')
            	choiceType('SINGLE_SELECT')
            	groovyScript {
                	fallbackScript('return["Script Error"]')
                	script('evaluate(new File("/var/lib/jenkins/scripts/getRepoList.groovy"))')

            }

        }
    		fileParam {
	 		name('SSHKeyFile')
			description('Please include your ssh file which we be deployed to the Bitbucket Repo.')
		}

     }
 
  steps {
        shell(
          'mv SSHKeyFile id_rsa.pub \n' +
		  'bash ${JENKINS_HOME}/scripts/deploySSHKeyIntoBitbucket.sh ${RepositoryName} ${SSHKeyFile} \n'
       )

    }
}
