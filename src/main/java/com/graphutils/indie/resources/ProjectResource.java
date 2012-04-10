package com.graphutils.indie.resources;

import com.graphutils.indie.handlers.ProjectRequestHandler;
import com.sun.jersey.spi.CloseableService;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import org.neo4j.server.database.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectResource extends Resource {
  
  private ProjectRequestHandler handler;

  public ProjectResource(@Context final CloseableService cs, @Context final Database db) {
    this.closeableService = cs;
    this.db = db;
    this.handler = new ProjectRequestHandler(new Neo4jGraph(db.graph));
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response ping(@PathParam("id") String id) {
    return getJson(handler.projectsFor(id));
  } 
  
  @POST
  public Response loadTinkerGraphProjects() {
    return post(handler.loadTinkerGraphData());
  }
}
