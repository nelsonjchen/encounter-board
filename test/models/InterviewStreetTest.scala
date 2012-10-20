package models

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

import InterviewStreet._
import scala.io.Source

class InterviewStreetTest extends Specification {
  "InterviewStreet" should {
    "parse" in {
      1 mustEqual 1
    }
  }

  def getJSON(name:String): String = {
    val is = getClass.getResourceAsStream(name)
    Source.fromInputStream(is).mkString
  }
}
