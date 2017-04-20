nestedView("${projectname}-View") {
    views {
        listView("${projectname}-WAR") {
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
    }
}

