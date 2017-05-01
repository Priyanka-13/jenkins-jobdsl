listView("Devops-Jobs") {
            jobs {
                names("Devops*")
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
        }
   }
