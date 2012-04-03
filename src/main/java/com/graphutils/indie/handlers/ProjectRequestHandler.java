package com.graphutils.indie.handlers;

import com.tinkerpop.blueprints.pgm.Graph;

import java.util.List;

public class ProjectRequestHandler {
  private Graph g;

  public ProjectRequestHandler(com.graphutils.indie.daos.GraphDatabase graphDatabase) {
    g = graphDatabase.g();
  }

  public StringRequestHandler projectsFor(final String id) {
    return new StringRequestHandler() {
      @Override
      public String handle() {
        List projects = com.graphutils.indie.traversals.BasicTraversals.findProjects(g, id);
        return new com.graphutils.indie.presenters.ProjectPresenter().toJson(projects);
      }
    };
  }

  public RequestHandler loadTinkerGraphData() {
    return new RequestHandler() {
      @Override
      public void handle() {
        com.graphutils.indie.daos.TinkerGraphData.populate(g);
      }
    };
  }
}
