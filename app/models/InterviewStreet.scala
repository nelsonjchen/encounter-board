package models

import play.api.libs.json.{JsObject, Json}
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html._
import play.api.libs.json.JsObject
import com.gargoylesoftware.htmlunit.Page


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

  def test_scrape(): List[Team] = {
    List[Team](Team("TeamFake",42,List[String]("TC","TB")),Team("TeamHorsePorn",44,List[String]("TB")))
  }

  def scrape(teamname: String, passcode: String, team_names:List[String]): List[Team] = {
    val teams = team_names.par.map(name => {
    val client = new WebClient()
    client.setJavaScriptEnabled(false)
    client.setThrowExceptionOnScriptError(false)
    val login_page: HtmlPage = client.getPage("https://ieee.interviewstreet.com/challenges/login/invitee")
    val form: HtmlForm = login_page.getForms.get(0)
    val name_input: HtmlTextInput = form.getInputByName("teamname")
    val code_input: HtmlPasswordInput = form.getInputByName("passcode")
    val submit_input: HtmlSubmitInput = form.getInputByName("submit")
    name_input.setValueAttribute(teamname)
    code_input.setValueAttribute(passcode)
    submit_input.click()
      val leader_url = "https://ieee.interviewstreet.com/challenges/rest/leaderboard/lid/default/page/1/json?filter=" + name
      val solved_url = "https://ieee.interviewstreet.com/challenges/rest/solved/handle/" + name
      val leader_src = client.getPage(leader_url).asInstanceOf[Page].getWebResponse.getContentAsString
      val solved_src = client.getPage(solved_url).asInstanceOf[Page].getWebResponse.getContentAsString
      parseTeam(leader_src,solved_src)
    }).filter(t => !t.name.equals("Unknown Team")).toList

    teams
  }
}
