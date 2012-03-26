package com.aurelius.indie.traversals;

import com.aurelius.indie.daos.TinkerGraphData;
import com.aurelius.indie.test.utils.AutoDbTestBase;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TraversalTest extends AutoDbTestBase {

  @Test
  public void shouldFindFriends() {
    TinkerGraphData.populate(g);

    // lets traverse to marko's 30+ year old friends' created projects
    List projects = BasicTraversals.findProjects(g, "1");

    assertEquals(2, projects.size());
    assertEquals("ripple", projects.get(0));
    assertEquals("lop", projects.get(1));
  }
}
