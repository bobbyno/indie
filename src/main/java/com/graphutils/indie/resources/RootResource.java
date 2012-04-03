package com.graphutils.indie.resources;

import com.graphutils.indie.daos.GraphDatabase;
import com.graphutils.indie.handlers.PingRequestHandler;
import com.sun.jersey.spi.CloseableService;
import org.neo4j.server.database.Database;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class RootResource extends Resource {

  private com.graphutils.indie.handlers.PingRequestHandler handler;

  public RootResource(@Context final CloseableService cs, @Context final Database db) {
    this.closeableService = cs;
    this.db = db;
    com.graphutils.indie.daos.GraphDatabase graphDatabase = com.graphutils.indie.daos.GraphDatabase.getInstance(db.graph);
    this.handler = new com.graphutils.indie.handlers.PingRequestHandler();
  }

  @GET
  @Path("ping")
  @Produces(MediaType.APPLICATION_JSON)
  public Response ping() {
    return getJson(handler.ping());
  }

}
