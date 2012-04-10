package com.example.traversals;

import com.example.daos.TinkerGraphData;
import com.graphutils.indie.test.base.AutoDbUnitTestBase;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TraversalTest extends AutoDbUnitTestBase {

  @Test
  public void shouldFindFriends() {
    TinkerGraphData.populate(g);

    // lets traverse to marko's 30+ year old friends' created projects
    List projects = ProjectTraversals.findProjects(g, "1");

    assertEquals(2, projects.size());
    assertEquals("ripple", projects.get(0));
    assertEquals("lop", projects.get(1));
  }
}
