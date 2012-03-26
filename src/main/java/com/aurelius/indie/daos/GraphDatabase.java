package com.aurelius.indie.daos;

import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Handles core db and index service management and initialization.
 */
public class GraphDatabase {

  private GraphDatabaseService db;
  private static Neo4jGraph g;

  private static GraphDatabase instance = null;

  public static GraphDatabase getInstance(final GraphDatabaseService graphGraphDatabaseService) {
    if (instance == null) {
      instance = new GraphDatabase(graphGraphDatabaseService);
    }
    return instance;
  }

  public GraphDatabase(final GraphDatabaseService graphGraphDatabaseService) {
    init(graphGraphDatabaseService);
  }

  public void shutdown() {
    if (db != null) {
      db.shutdown();
    }
  }

  public GraphDatabaseService graphDatabaseService() {
    return db;
  }

  public Neo4jGraph g() {
    return g;
  }

  private void init(GraphDatabaseService graphDb) {
    this.db = graphDb;
    g = new Neo4jGraph(graphDb);

    instance = this;
  }
}
