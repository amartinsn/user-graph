package com.globo.cadun.graph

import org.anormcypher.Cypher

/**
 * Created by amartins on 2/25/14.
 */
case class Entity(entityType: String, name: String, url: String="")

object Preference {

  def create(userId: Long, preferenceName: String, entity: Entity) = {
    val query = s"""MERGE (ee:${entity.entityType}{ name:{entityName}, url:{entityUrl} })
        WITH ee
        MATCH (u:User{ id:{userId} })
        MERGE (u)-[:$preferenceName]->(ee)"""
    Cypher(query).on("entityName" -> entity.name, "entityUrl" -> entity.url, "userId" -> userId)()
  }

  def delete(userId: Long, preferenceName: String, entity: Entity) = {
    val query = s"match (u:User{ id:{userId} })-[r:$preferenceName]-(e:${entity.entityType}{ name:{entityName} }) delete r"
    Cypher(query).on("userId" -> userId, "entityName" -> entity.name)()
  }

  def list(userId: Long, preferenceName: String, entityType: String) = {
    Cypher(s"match (u:User{ id:{userId} })-[:$preferenceName]->(e:$entityType) return e.name as name, e.url as url")
      .on("userId" -> userId)().map(row => {
      Entity(entityType, row[String]("name"), row[String]("url"))
    }).toList
  }

  def main(args: Array[String]) {
//    User.deleteAll()
//
//    User.create(1, "amartins", "amartins@corp.globo.com", "Alexandre Martins")
//    User.create(2, "guguinha", "guguinha@corp.globo.com", "Victor Costa")
//    User.create(3, "rodrigo.dealer", "rodrigo@corp.globo.com", "Rodrigo Oliveira")
//    User.create(4, "andre.goncalves", "andre.goncalves@corp.globo.com", "André Gonçalves")
//
//    User.makeFriends(1, 3)
//    User.makeFriends(1, 4)
//    User.makeFriends(2, 4)
//    User.makeFriends(3, 2)

//    Preference.create(1, "follows", Entity("Section", "Cars", "http://g1.globo.com/carros"))
//    Preference.create(1, "follows", Entity("Section", "Life Style", "http://g1.globo.com/brasil"))
//    Preference.create(1, "follows", Entity("Section", "Economy", "http://g1.globo.com/economia"))
//    Preference.create(1, "follows", Entity("Section", "Music", "http://g1.globo.com/music"))

//    Preference.create(2, "follows", Entity("Section", "Politics", "http://g1.globo.com/politica"))
//    Preference.create(2, "follows", Entity("Section", "Economy", "http://g1.globo.com/economia"))
//
//    Preference.create(3, "follows", Entity("Section", "Economy", "http://g1.globo.com/economia"))
//
//    Preference.create(4, "follows", Entity("Section", "World", "http://g1.globo.com/mundo"))
//    Preference.create(4, "follows", Entity("Section", "Music", "http://g1.globo.com/music"))
//    Preference.create(4, "follows", Entity("Section", "Nature", "http://g1.globo.com/natureza"))
//    Preference.create(4, "follows", Entity("Section", "Life Style", "http://g1.globo.com/brasil"))

    System.exit(1)
  }
}
