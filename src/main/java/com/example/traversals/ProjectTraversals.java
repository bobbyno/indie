package com.example.traversals;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;

import java.util.List;

public class ProjectTraversals {
  public static List<String> findProjects(Graph g, String id) {
    return (List<String>) new GremlinPipeline(g.getVertex(id)).out("knows").filter(new PipeFunction<Vertex, Boolean>() {
      @Override
      public Boolean compute(Vertex vertex) {
        Integer age = (Integer) vertex.getProperty("age");
        return age != null && age > 30;
      }
    }).out("created").property("name").toList();
  }
}
