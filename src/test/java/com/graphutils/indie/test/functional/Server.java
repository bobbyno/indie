package com.graphutils.indie.test.functional;

import com.graphutils.indie.test.utils.FileUtils;

public class Server {
  private static String databaseFiles;
  private String neo4jCommand;
  private static boolean justStarted = true;

  public Server() {
    String version = FileUtils.readServerVersion();
    databaseFiles = String.format("%s/data/graph.db", version);
    neo4jCommand = String.format("%s/bin/neo4j", version);
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

      System.out.println(String.format("Starting neo4j: %s", neo4jCommand));
      runtime.exec(String.format("%s start", neo4jCommand)).waitFor();

      System.out.println("Pausing for 5s while the server starts...");
      Thread.sleep(5000);

      justStarted = false;
    }
  }
}
