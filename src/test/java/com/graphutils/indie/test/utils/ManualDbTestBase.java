package com.graphutils.indie.test.utils;

import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.server.database.Database;

import java.io.File;
import java.util.Date;

public class ManualDbTestBase {
  protected Transaction transaction;
  protected static String dbFilename;
  protected static Database database;
  protected static Neo4jGraph g;

  @BeforeClass
  public static void initializeDatabase() {
    dbFilename = "out/graph_db-" + new Date().getTime();
    database = new Database(new EmbeddedGraphDatabase(dbFilename));
    g = new Neo4jGraph(database.graph);

  }

  @AfterClass
  public static void closeDatabase() {
    database.shutdown();
    File dbFile = new File(dbFilename);
    dbFile.delete();
  }

  public void startTransaction() {
    transaction = database.graph.beginTx();
  }

  public void rollbackTransaction() {
    transaction.failure();
    transaction.finish();
  }

  public void commitTransaction() {
    transaction.success();
    transaction.finish();
  }
}
