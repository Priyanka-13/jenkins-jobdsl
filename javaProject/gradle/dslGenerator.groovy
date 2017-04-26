import groovy.text.* 
import java.io.* 

import hudson.model.*
def projectname = System.getenv("ProjectName")
def appurl = System.getenv("AppURL")
def gitbranch = System.getenv("GitBranch")
def gradletask = System.getenv("GradleTask")
def WORKSPACE = System.getenv("WORKSPACE")

//println "$projectname"

new File("${WORKSPACE}/javaProject/gradle/dslfiles").mkdirs()  


new File("/var/lib/jenkins/workspace/seedJob/javaProject/gradle/dslTemplates").eachFile() { file->
      files = file.getName()
     
    if(files.matches("Job(.*)")) {
        //println files
      
        def newfile = new File("${WORKSPACE}/javaProject/gradle/dslTemplates/${files}")
        def binding = ['projectname' : projectname , 'appurl' : appurl, 'gitbranch' : gitbranch, 'gradletask': gradletask ]

        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(newfile)
        def writable = template.make(binding)
 
	new File("${WORKSPACE}/javaProject/gradle/dslfiles","${files}").withWriter('utf-8') { 
	         writer -> writer.write (writable) 
  }
 }
}
