listView("${projectname}-Jobs") {
            jobs {
                names("${projectname}CodeStability", "${projectname}CodeDeploy")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
