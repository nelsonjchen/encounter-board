package models

case class Team(name: String, rank: Int, complete: List[String]) extends Ordered[Team] {
  def compare(that: Team) = rank.compare(that.rank)
}

object InterviewStreet {
  def parseTeam(leader_json: String, solved_json: String): Team = {
    Team("TestTeam", 45, List[String]())
  }

  def scores():List[Team] = {
    List[Team]()
  }
}
