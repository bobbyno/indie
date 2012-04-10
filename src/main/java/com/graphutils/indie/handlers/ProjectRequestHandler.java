package com.graphutils.indie.handlers;

import com.graphutils.indie.daos.TinkerGraphData;
import com.graphutils.indie.presenters.ProjectPresenter;
import com.graphutils.indie.traversals.BasicTraversals;
import com.tinkerpop.blueprints.pgm.Graph;

import java.util.List;

public class ProjectRequestHandler {
  private Graph g;

  public ProjectRequestHandler(Graph g) {
    this.g = g;
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
