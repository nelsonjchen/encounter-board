package controllers

import play.api._
import cache.Cache
import libs.json.{JsValue, Json}
import play.api.mvc._
import libs.concurrent.Akka
import models.InterviewStreet
import play.api.Play.current
import Json.toJson
import models.Team

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hack_dash = Action {
    val promiseOfTeams = Akka.future {
      InterviewStreet.test_scrape()
    }
    Async {
      promiseOfTeams.map(lt => {
        Ok(views.html.dash(lt))
      })
    }
  }

  def dash = Action {
    val promiseOfTeams = Akka.future {
      val team_name = current.configuration.getString("ieeextreme.login").get
      val team_code = current.configuration.getString("ieeextreme.code").get
      val team_list = current.configuration.getString("ieeextreme.teams").get.split(":").toList

      val team = Cache.getOrElse[List[Team]]("dash", 60){
        InterviewStreet.scrape(team_name, team_code, team_list)
      }
      team
    }
    Async {
      promiseOfTeams.map(lt => {

        Ok(views.html.dash(lt))
      })
    }
  }

}