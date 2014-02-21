package com.globo.cadun.graph

import org.anormcypher.Cypher
import com.twitter.util.Future

/**
 * Created by amartins on 2/18/14.
 */
object UserRepository {

  def create(id: Long, username: String, email: String, name: String) = {
    Future(
      Cypher("""create (u:User { id:{id}, username: {username}, email: {email}, name: {name} });""")
        .on("id"->id, "username"->username, "email"->email, "name"->name).apply()
    )
  }

  def all() = {
    val req = Cypher("""match (n:User) return n.id as id, n.username as username, n.email as email, n.name as name;""")
    Future(
      req().map(row => {
        User(row[Long]("id"), row[String]("username"), row[String]("email"), row[String]("name"))
      }).toList
    )
  }

  def find(id: Long) = {
    val req = Cypher("""match (u:User) where u.id={id} return u.id as id, u.username as username, u.email as email, u.name as name;""")
    Future(
      req.on("id"->id).single()
//        User(row[Long]("id"), row[String]("username"), row[String]("email"), row[String]("name"))

    )
  }

  def deleteAll() = {
    Future(
      Cypher("""match (n) optional match n-[r]-() delete n, r;""")()
    )
  }

  def main(args: Array[String]) {
    val req = Cypher("""match (u:User) where u.id={id} return u.id as id, u.username as username, u.email as email, u.name as name;""")
    req.on("id"->id).
  }
}
