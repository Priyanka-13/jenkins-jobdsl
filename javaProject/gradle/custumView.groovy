def thr = Thread.currentThread()
def build = thr.executable
// get build parameters
def buildVariablesMap = build.buildVariables
String projectname = buildVariablesMap?.ProjectName

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

