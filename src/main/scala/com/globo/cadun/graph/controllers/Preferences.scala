package com.globo.cadun.graph.controllers

import com.twitter.finatra.Controller
import com.twitter.util.Future
import scala.util.parsing.json.JSON

/**
 * Created by amartins on 2/23/14.
 */
class Preferences extends Controller {

  post("/users/:id/preferences") { request =>
    // ******* DE:
    //  {
    //    "follows": {
    //      "section": [
    //        {"name": "foo", "url": "http://foo.bar"},
    //        {"name": "foo", "url": "http://foo.bar"}
    //      ]
    //    },
    //    "supports": {
    //      "soccer_team": [{"name": "Flamengo", "url": "http://ge.globo.com/flamengo"}],
    //      "rugby_team": [{"name": "Wallabies"}]
    //    }
    //  }
    //
    // ******* PARA:
    //  {
    //    "follows": [
    //      Entity("Section", "foo", "http://foo.bar"),
    //      Entity("Section", "lee", "http://lee.bar")
    //    ],
    //    "supports": [
    //      Entity("SoccerTeam", "Flamengo", "http://ge.globo.com/flamengo"),
    //      Entity("RugbyTeam", "Wallabies")
    //    ]
    //  }

      val jsonValue = JSON.parseFull(request.getContentString()).get.asInstanceOf[Map[String, Any]]

      for ((k, v) <- jsonValue) println(s"key: $k | value: $v")

//      val param = for {
//        id <- user.get("id")
//        name <- user.get("name")
//        email <- user.get("email")
//        username <- user.get("username")
//      } yield ((id, name, email, username))

//      val result = param match {
//        case Some((id, name, email, username)) => User.create(id.toLong, username, email, name)
//        case None => Future.exception(new IllegalArgumentException("Unknown user"))
//      }
//
//      result.map(_ => render.plain("").status(201))
    Future.exception(new IllegalArgumentException("Unknown user"))
  }
}
