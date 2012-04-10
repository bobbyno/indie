package com.example.handlers;

import com.example.daos.TinkerGraphData;
import com.example.traversals.ProjectTraversals;
import com.graphutils.indie.handlers.RequestHandler;
import com.graphutils.indie.handlers.StringRequestHandler;
import com.example.presenters.ProjectPresenter;
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
        List projects = ProjectTraversals.findProjects(g, id);
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
