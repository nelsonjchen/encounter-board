package models

import play.api.libs.json.Json

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

    Team(name, rank, List[String]())
  }

  def scores():List[Team] = {
    List[Team]()
  }
}
