listView("${projectname}-Jobs") {
            jobs {
                names("${projectname}CodeStability")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
listView("Deploy-Jobs") {
            jobs {
                names("CodeDeploy")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
