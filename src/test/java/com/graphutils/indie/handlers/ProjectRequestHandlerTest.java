package com.graphutils.indie.handlers;

import com.graphutils.indie.daos.TinkerGraphData;
import com.graphutils.indie.test.utils.AutoDbTestBase;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProjectRequestHandlerTest extends AutoDbTestBase {
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
