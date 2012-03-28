package com.aurelius.indie.test.functional;

import org.junit.BeforeClass;

public class FunctionalTestBase {

  protected Client client = new Client();

  @BeforeClass
  public static void startServer() throws Exception {
    Server server = new Server();
    server.start();
  }
}
