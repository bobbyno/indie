package com.example.test.functional;

import com.graphutils.indie.test.functional.FunctionalTestBase;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


public class ProjectTest extends FunctionalTestBase {
  @Test
  public void shouldRetrieveAListOfProjects() {
    assertEquals(Response.Status.CREATED.getStatusCode(), client.post("projects").getStatus());
    assertEquals(Response.Status.OK.getStatusCode(), client.getJson("projects/1").getStatus());
  }
}
