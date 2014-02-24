package com.globo.cadun.graph

import com.twitter.finatra.FinatraServer

case class User(id: Long, username: String, email: String, name: String)

object App extends FinatraServer {
  register(new UserController())
  register(new Friendships())
  register(new UserPreferences())
}

