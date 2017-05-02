job("Devops-CI-destroyer") {
  description('This job will delete all jobs and view related to a given project')
  parameters {
        stringParam('ProjectName', '', 'Name of the Project that you want to delete.')  
  }

  steps {
        systemGroovyCommand( 
          	'import hudson.model.ListView \n' +
			'import jenkins.model.* \n' +

			'def projectname = build.buildVariableResolver.resolve("ProjectName") \n' +
			'view_name = projectname + "-Jobs"; \n' +

			'def projectDir = \'/var/lib/jenkins/BUILD/\' + projectname \n' +

			'def mainDir = new File("$projectDir") \n' +
			'mainDir.deleteDir() \n' +

			'vc = Jenkins.instance.getView(view_name).getOwner(); \n' +
			'v = vc.getView(view_name); \n' +
          	
          	'//delete jobs belonging to the view \n' +
			'for(view in v.getItems()) { \n' +
				'print "removing $view " \n' +
			'//  view.delete(); \n' +
			'println "deleteing file $mainDir" \n' + 
			'mainDir.deleteDir() \n' +
			'} \n' +
			'//vc.deleteView(v); \n' +
			'print "$view_name removed "; \n' 
                           
        ) 
    }
  
}

