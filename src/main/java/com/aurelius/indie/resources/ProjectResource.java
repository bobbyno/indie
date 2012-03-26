package com.aurelius.indie.resources;

import com.aurelius.indie.daos.GraphDatabase;
import com.aurelius.indie.handlers.ProjectRequestHandler;
import com.sun.jersey.spi.CloseableService;
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
    GraphDatabase graphDatabase = GraphDatabase.getInstance(db.graph);
    this.handler = new ProjectRequestHandler(graphDatabase);
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
