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
    "parse TeamJacob correctly" in {
      val leader_jacob = getJSON("leader_jacob.json")
      val solved_one_jacob = getJSON("solved_one_jacob.json")
      val team = parseTeam(leader_jacob,solved_one_jacob)
      team.name must equalTo("TeamJacob")
      team.rank must equalTo(58)
      team.complete.length must equalTo(1)
    }
  }

  def getJSON(name:String): String = {
    val is = getClass.getResourceAsStream(name)
    Source.fromInputStream(is).mkString
  }
}
