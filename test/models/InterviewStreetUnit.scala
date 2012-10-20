package models

import org.specs2.mutable._
import org.specs2.matcher.StringMatchers.contain
import play.api.test._
import play.api.test.Helpers._

import InterviewStreet._
import scala.io.Source

class InterviewStreetUnit extends Specification {
  "InterviewStreet" should {
    "be tested with a resource loader that works" in {
      val json = getJSON("leader_hk.json")
      json must contain("models")
    }
  }

  def getJSON(name:String): String = {
    val is = getClass.getResourceAsStream(name)
    Source.fromInputStream(is).mkString
  }
}
