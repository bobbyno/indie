package com.aurelius.indie.handlers;

import com.aurelius.indie.daos.GraphDatabase;
import com.aurelius.indie.daos.TinkerGraphData;
import com.aurelius.indie.presenters.ProjectPresenter;
import com.aurelius.indie.traversals.BasicTraversals;
import com.tinkerpop.blueprints.pgm.Graph;

import java.util.List;

public class ProjectRequestHandler {
  private Graph g;

  public ProjectRequestHandler(GraphDatabase graphDatabase) {
    g = graphDatabase.g();
  }

  public StringRequestHandler projectsFor(final String id) {
    return new StringRequestHandler() {
      @Override
      public String handle() {
        List projects = BasicTraversals.findProjects(g, id);
        return new ProjectPresenter().toJson(projects);
      }
    };
  }

  public RequestHandler loadTinkerGraphData() {
    return new RequestHandler() {
      @Override
      public void handle() {
        TinkerGraphData.populate(g);
      }
    };
  }
}
