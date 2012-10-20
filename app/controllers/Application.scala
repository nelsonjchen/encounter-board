package controllers

import play.api._
import play.api.mvc._
import libs.concurrent.Akka
import models.InterviewStreet
import play.api.Play.current

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hack_dash = Action {
    val promiseOfTeams = Akka.future {
      InterviewStreet.test_scrape()
    }
    Async{
      promiseOfTeams.map(lt => Ok(lt.toString()))
    }
  }
  
}