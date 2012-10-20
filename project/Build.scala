import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "encounter-board"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.apache.httpcomponents" % "httpclient" % "4.2.1",
      "net.sourceforge.htmlunit" % "htmlunit" % "2.10",
      "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
