listView("Devops-Jobs") {
            jobs {
                names("DevopsDeploySSHKey", "Devops-CI-creator")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
