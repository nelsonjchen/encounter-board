package models

case class Team(name: String, rank: Int, complete: List[String])

object InterviewStreet {
  def parseTeam(leader_json: String, solved_json: String): Team = {
    Team("TestTeam", 45, List[String]())
  }

  def scores():List[Team] = {
    List[Team]()
  }
}
