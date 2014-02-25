package com.globo.cadun.graph.repositories

import org.anormcypher.Cypher

/**
 * Created by amartins on 2/24/14.
 */
object PreferencesRepository {

  //  HTTP POST /users/:id/preferences
  //    { "follows": { "section": { "name": "Cars" } } }

  def create(userId: Long, entityType: String, name: String) = {
    Cypher(
      """
        MERGE (ee:{entityType}{ name:{name} })
        WITH ee
        MATCH (u:User{ id:{userId} })
        MERGE (u)-[:{relationName}]->(ee)
      """).on("entityType" -> entityType, "name" -> name, "userId" -> userId, "relationName" -> "follows")()
  }

  def delete(userId: Long, entityType: String, name: String) = {
    Cypher("""match (u:User{ id:{userId} })-[r:{relationName}]-(e:Section{ name:{name} }) delete r""")
      .on("userId" -> userId, "relationName" -> "follows", "name" -> name)()
  }

  def main(args: Array[String]) {
    PreferencesRepository.delete(3, "Section", "Cars")

    System.exit(1)
  }
}
