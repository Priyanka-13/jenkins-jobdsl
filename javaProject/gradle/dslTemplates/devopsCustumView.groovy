listView("Devops-Jobs") {
            jobs {
                names("DevopsDeploySSHKey", "Devops-CI-creator", "Devops-CI-destroyer")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
