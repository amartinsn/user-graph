package com.globo.cadun.graph.repositories

import org.anormcypher.Cypher

/**
 * Created by amartins on 2/24/14.
 */
object PreferencesRepository {

  //  HTTP POST /users/:id/preferences
  //    { "follows": { "section": { "name": "Cars" } } }

  def create(userId: Long, preferenceName: String, entityType: String, entityName: String) = {
    val query = s"""MERGE (ee:$entityType{ name:{entityName} })
        WITH ee
        MATCH (u:User{ id:{userId} })
        MERGE (u)-[:$preferenceName]->(ee)"""
    Cypher(query).on("entityName" -> entityName, "userId" -> userId)()
  }

  def delete(userId: Long, preferenceName: String, entityType: String, entityName: String) = {
    val query = s"match (u:User{ id:{userId} })-[r:$preferenceName]-(e:$entityType{ name:{entityName} }) delete r"
    Cypher(query).on("userId" -> userId, "entityName" -> entityName)()
  }

  def main(args: Array[String]) {
    PreferencesRepository.create(3, "follows", "Section", "Cars")
    PreferencesRepository.create(4, "follows", "Section", "Cars")
//    PreferencesRepository.delete(3, "follows", "Section", "Cars")

    System.exit(1)
  }
}
