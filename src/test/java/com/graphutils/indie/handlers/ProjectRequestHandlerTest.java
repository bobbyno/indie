package com.graphutils.indie.handlers;

import com.graphutils.indie.daos.TinkerGraphData;
import com.graphutils.indie.handlers.ProjectRequestHandler;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProjectRequestHandlerTest extends com.graphutils.indie.test.utils.AutoDbTestBase {
  @Test
  public void shouldFindProjects() {
    com.graphutils.indie.daos.TinkerGraphData.populate(g);
    com.graphutils.indie.handlers.ProjectRequestHandler handler = new com.graphutils.indie.handlers.ProjectRequestHandler(graphDb);
    String results = handler.projectsFor("1").handle();
    assertEquals("{\"projects\":[\"ripple\",\"lop\"]}", results);
  }
  
  @Test
  public void shouldLoadProjects() {
    com.graphutils.indie.handlers.ProjectRequestHandler handler = new com.graphutils.indie.handlers.ProjectRequestHandler(graphDb);

    // initially, we start off only with the Neo4j root node in adv. and enterprise edition, but not community
    assertTrue(vCount() <= 1);

    handler.loadTinkerGraphData().handle();
    long count = vCount();
    assertTrue(count == 6 || count == 7);
  }

  private long vCount() {
    return new GremlinPipeline(g.getVertices()).count();
  }
}
