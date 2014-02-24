package com.globo.cadun.graph

import org.anormcypher._
import com.twitter.util.Future

/**
 * Created by amartins on 2/18/14.
 */
object UserRepository {

  def create(id: Long, username: String, email: String, name: String) = {
    Future(
      Cypher( """create (u:User { id:{id}, username: {username}, email: {email}, name: {name} });""")
        .on("id" -> id, "username" -> username, "email" -> email, "name" -> name).apply()
    )
  }

  def all() = {
    val req = Cypher("""match (n:User) return n;""")
    Future(
      req().map(row => {
        User(row[Long]("id"), row[String]("username"), row[String]("email"), row[String]("name"))
      }).toList
    )
  }

//  def createRelation(something: Any, somethingElse: Any, relationName: String) = {
//    Cypher(
//      """
//        match (u:User {id: {userId}}), (o:User {id: {otherUserId}})
//        create unique (u)-[:friends]->(o);
//      """).on("userId" -> user.id, "otherUserId" -> otherUser.id)()
//  }

  def makeFriends(userId: Long, otherUserId: Long) = {
    Future(
      Cypher(
        """
        match (u:User {id: {userId}}), (o:User {id: {otherUserId}})
        create unique (u)-[:friends]->(o);
        """).on("userId" -> userId, "otherUserId" -> otherUserId)()
    )
  }

  def unfriend(userId: Long, otherUserId: Long) = {
    Future(
      Cypher("""match (n:User{ id:{userId} })-[r:friends]-(o:User{ id:{otherUserId} }) delete r""")
        .on("userId" -> userId, "otherUserId" -> otherUserId)()
    )
  }

  def listFriendsOf(userId: Long) = {
    Future(
      Cypher("""match (u:User{ id:{userId} })-[:friends]-(f) return f.id as id, f.name as name, f.email as email, f.username as username""")
        .on("userId"->userId)().map(row => {
        User(row[Long]("id"), row[String]("username"), row[String]("email"), row[String]("name"))
      }).toList
    )
  }

  def deleteAll() = {
    Future( Cypher("""match (n) optional match n-[r]-() delete n, r;""")() )
  }

//  def main(args: Array[String]) {
//    val alex = User(1, null, null, null)
//    val cacau = User(3, null, null, null)
//
////    UserRepository.makeFriends(alex.id, cacau.id)
////    UserRepository.makeFriends(alex.id, cacau.id)
//    println(UserRepository.listFriendsOf(alex))
//
//    System.exit(1)
//  }
}
