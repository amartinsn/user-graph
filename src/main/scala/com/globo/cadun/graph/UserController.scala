package com.globo.cadun.graph

import com.twitter.finatra.Controller
import com.twitter.util.Future
import scala.util.parsing.json.{JSONType, JSON}
import com.globo.cadun.graph.repositories.UserRepository

/**
 * Created by amartins on 2/20/14.
 */
class UserController extends Controller {

  get("/") { request =>
    render.plain("welcome to CadunGraph").toFuture
  }

  get("/users") {
    request => for {
      users <- UserRepository.all()
    } yield (render.json(users))
  }

  post("/users") {
    request =>
      val user: Map[String, String] = JSON.parseFull(request.getContentString()).get.asInstanceOf[Map[String, String]]

      val param = for {
        id <- user.get("id")
        name <- user.get("name")
        email <- user.get("email")
        username <- user.get("username")
      } yield ((id, name, email, username))

      val result = param match {
        case Some((id, name, email, username)) => UserRepository.create(id.toLong, username, email, name)
        case None => Future.exception(new IllegalArgumentException("Unknown user"))
      }

      result.map(_ => render.plain("").status(201))
  }

  error { request =>
    request.error match {
      case Some(e:IllegalArgumentException) =>
        render.status(400).plain(e.getMessage).toFuture
      case _ =>
        render.status(500).plain("Something went wrong!").toFuture
    }
  }
}
