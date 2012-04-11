package com.graphutils.indie.test.functional;

import java.io.IOException;

public class Server {
  private static String databaseFiles;
  private String neo4jCommand;
  private static boolean running = false;

  public Server() {
    String version = Finder.readServerVersion();
    databaseFiles = String.format("%s/data/graph.db", version);
    neo4jCommand = String.format("%s/bin/neo4j", version);
  }

  public void start() throws Exception {
    if (running) return;

    final Runtime runtime = Runtime.getRuntime();

    runtime.addShutdownHook(new Thread() {
      @Override
      public void run() {        
        try {
          clean(runtime);
          Server.this.stop(runtime);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    clean(runtime);
    start(runtime);   
  }

  private void stop(Runtime runtime) throws InterruptedException, IOException {
    System.out.println("Stopping Neo4j...");
    runtime.exec(String.format("%s stop", neo4jCommand)).waitFor();
  }

  private void start(Runtime runtime) throws InterruptedException, IOException {
    System.out.println(String.format("Starting neo4j: %s", neo4jCommand));
    runtime.exec(String.format("%s start", neo4jCommand)).waitFor();
    running = true;
  }

  private void clean(Runtime runtime) throws InterruptedException, IOException {
    System.out.println("Clearing the database...");
    runtime.exec(String.format("rm -rf %s", databaseFiles)).waitFor();
  }
}
