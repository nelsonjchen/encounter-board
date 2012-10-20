package models

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

import InterviewStreet._
import scala.io.Source

class InterviewStreetTest extends Specification {
  "InterviewStreet" should {
    "be tested with a resource loader that works" in {

    }
  }

  def getJSON(name:String): String = {
    val is = getClass.getResourceAsStream(name)
    Source.fromInputStream(is).mkString
  }
}
