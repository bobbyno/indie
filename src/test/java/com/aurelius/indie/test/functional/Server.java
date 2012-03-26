package com.aurelius.indie.test.functional;

import java.io.File;
import java.io.IOException;

public class Server {
  private static String databaseFiles;
  private String neo4jCommand;
  private static boolean justStarted = true;

  public Server() {
    String cwd = null;
    try {
      cwd = new File(".").getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String version = "neo4j-community-1.6.1";
    databaseFiles = String.format("%s/neo4j-server/%s/data/graph.db", cwd, version);
    neo4jCommand = String.format("%s/neo4j-server/%s/bin/neo4j", cwd, version);
  }

  public void start() throws Exception {
    final Runtime runtime = Runtime.getRuntime();

    if (justStarted) {
      runtime.addShutdownHook(new Thread() {
        @Override
        public void run() {
          System.out.println("Stopping Neo4j...");
          try {
            System.out.println("Clearing the database...");
            runtime.exec(String.format("rm -rf %s", databaseFiles)).waitFor();
            runtime.exec(String.format("%s stop", neo4jCommand)).waitFor();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });

      System.out.println("Clearing the database...");
      runtime.exec(String.format("rm -rf %s", databaseFiles)).waitFor();

      runtime.exec(String.format("%s start", neo4jCommand)).waitFor();

      System.out.println("Pausing for 5s while the server starts...");
      Thread.sleep(5000);

      justStarted = false;
    }
  }
}
