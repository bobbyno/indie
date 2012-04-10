package com.example.daos;

import com.graphutils.indie.test.base.AutoDbUnitTestBase;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class TinkerGraphDataTest extends AutoDbUnitTestBase {

  @Test
  public void shouldPopulateGraphOnce() {
    TinkerGraphData.populate(g);
    // ensure that the call is idempotent
    TinkerGraphData.populate(g);
    long count = new GremlinPipeline().start(g.getVertices()).count();

    // neo4j's root node in adv. and ent. edition, along with 6 in the TinkerGraph
    assertTrue(count == 6 || count == 7);
  }
}
