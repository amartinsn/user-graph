package com.globo.cadun.graph

import com.twitter.finatra.Controller
import com.twitter.util.Future

/**
 * Created by amartins on 2/23/14.
 */
class Friendships extends Controller {
  get("/users/:id/friends") { request =>
    val userId = request.routeParams.get("id")

    val result = userId match {
      case Some(userId) => UserRepository.listFriendsOf(userId.toLong)
      case None => Future.exception(new IllegalArgumentException("User id not provided"))
    }

    result.map(friends => render.json(friends))
  }

  post("/friendships/:userId-:otherUserId") { request =>
    val friendship = for {
      userId <- request.routeParams.get("userId")
      otherUserId <- request.routeParams.get("otherUserId")
    } yield ((userId, otherUserId))

    val result = friendship match {
      case Some((userId, otherUserId)) => UserRepository.makeFriends(userId.toLong, otherUserId.toLong)
      case None => Future.exception(new IllegalArgumentException("Invalid friendship representation"))
    }

    result.map(_ => render.status(201))
  }

  delete("/friendships/:userId-:otherUserId") { request =>
    val friendship = for {
      userId <- request.routeParams.get("userId")
      otherUserId <- request.routeParams.get("otherUserId")
    } yield ((userId, otherUserId))

    val result = friendship match {
      case Some((userId, otherUserId)) => UserRepository.unfriend(userId.toLong, otherUserId.toLong)
      case None => Future.exception(new IllegalArgumentException("Invalid friendship representation"))
    }

    result.map(_ => render.status(204))
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
