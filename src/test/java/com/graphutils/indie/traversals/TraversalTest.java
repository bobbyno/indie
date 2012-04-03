package com.graphutils.indie.traversals;

import com.graphutils.indie.daos.TinkerGraphData;
import com.graphutils.indie.traversals.BasicTraversals;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TraversalTest extends com.graphutils.indie.test.utils.AutoDbTestBase {

  @Test
  public void shouldFindFriends() {
    com.graphutils.indie.daos.TinkerGraphData.populate(g);

    // lets traverse to marko's 30+ year old friends' created projects
    List projects = com.graphutils.indie.traversals.BasicTraversals.findProjects(g, "1");

    assertEquals(2, projects.size());
    assertEquals("ripple", projects.get(0));
    assertEquals("lop", projects.get(1));
  }
}
