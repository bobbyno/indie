package com.graphutils.indie.test.functional;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class PingTest extends FunctionalTestBase {

  @Test
  public void shouldPingToEnsureTheUnmanagedExtensionIsLoaded() {
    ClientResponse response = client.getJson("ping");
    assertEquals("Ensure that the extension is correctly configured and that you've run 'ant jar'",
        Response.Status.OK.getStatusCode(), response.getStatus());
  }
}
