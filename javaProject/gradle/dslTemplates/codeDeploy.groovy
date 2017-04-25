
 job("CodeDeploy") {
  description("Code deploy Job ")
  logRotator {
        daysToKeep(60)
        numToKeep(20)
        artifactDaysToKeep(1)
    }
  steps {
	steps {
        shell('sudo ansible-playbook -i /etc/ansible/roles/code/hosts /etc/ansible/roles/code/site.yml')
    }

    }
}

