package models

import play.api.libs.json.{JsObject, Json}
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlPasswordInput, HtmlTextInput, HtmlForm, HtmlPage}

case class Team(name: String, rank: Int, complete: List[String]) extends Ordered[Team] {
  def compare(that: Team) = rank.compare(that.rank)
}

object InterviewStreet {
  def parseTeam(leader_json: String, solved_json: String): Team = {
    val leader = Json.parse(leader_json)
    val solved = Json.parse(solved_json)

    val team_leader = (leader \ "models")(0)
    val rank = (team_leader \ "rank").asOpt[Int].getOrElse(9999)
    val name = (team_leader \ "details" \ "handle").asOpt[String].getOrElse("Unknown Team")

    val complete = (solved \ "model") match {
      case JsObject(fields) => fields.toMap.map(t => t._2.asOpt[String].getOrElse("Unknown Problem"))
      case _ => List[String]()
    }

    Team(name, rank, complete.toList)
  }

  def scores(): List[Team] = {
    List[Team]()
  }

  def scrape(teamname: String, passcode: String): List[Team] = {
    val client = new WebClient()
    client.setJavaScriptEnabled(true)
    client.setThrowExceptionOnScriptError(false)
    val login_page: HtmlPage = client.getPage("https://ieee.interviewstreet.com/challenges/login/invitee")
    val form: HtmlForm = login_page.getForms.get(0)
    val name_input: HtmlTextInput = form.getInputByName("teamname")
    val code_input: HtmlPasswordInput = form.getInputByName("passcode")
    name_input.setValueAttribute(teamname)
    code_input.setValueAttribute(passcode)




    List[Team]()
  }
}
