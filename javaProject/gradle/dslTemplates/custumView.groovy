def thr = Thread.currentThread()
def build = thr.executable
// get build parameters
def buildVariablesMap = build.buildVariables
String projectname = buildVariablesMap?.ProjectName

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
