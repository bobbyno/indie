package com.graphutils.indie.resources;

import com.sun.jersey.spi.CloseableService;
import org.neo4j.server.database.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectResource extends Resource {
  
  private com.graphutils.indie.handlers.ProjectRequestHandler handler;

  public ProjectResource(@Context final CloseableService cs, @Context final Database db) {
    this.closeableService = cs;
    this.db = db;
    com.graphutils.indie.daos.GraphDatabase graphDatabase = com.graphutils.indie.daos.GraphDatabase.getInstance(db.graph);
    this.handler = new com.graphutils.indie.handlers.ProjectRequestHandler(graphDatabase);
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
