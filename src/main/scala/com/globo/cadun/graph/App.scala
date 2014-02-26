package com.globo.cadun.graph

import com.twitter.finatra.FinatraServer
import com.globo.cadun.graph.controllers.{Users, Preferences, Friendships}

object App extends FinatraServer {
  register(new Users())
  register(new Friendships())
  register(new Preferences())
}

