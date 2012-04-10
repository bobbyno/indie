package com.example.handlers;

import com.example.daos.TinkerGraphData;
import com.graphutils.indie.test.base.AutoDbUnitTestBase;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProjectRequestHandlerTest extends AutoDbUnitTestBase {
  @Test
  public void shouldFindProjects() {
    TinkerGraphData.populate(g);
    ProjectRequestHandler handler = new ProjectRequestHandler(g);
    String results = handler.projectsFor("1").handle();
    assertEquals("{\"projects\":[\"ripple\",\"lop\"]}", results);
  }

  @Test
  public void shouldLoadProjects() {
    ProjectRequestHandler handler = new ProjectRequestHandler(g);

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
