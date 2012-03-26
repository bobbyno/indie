package com.aurelius.indie.resources;

import com.aurelius.indie.daos.GraphDatabase;
import com.aurelius.indie.handlers.PingRequestHandler;
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

  private PingRequestHandler handler;

  public RootResource(@Context final CloseableService cs, @Context final Database db) {
    this.closeableService = cs;
    this.db = db;
    GraphDatabase graphDatabase = GraphDatabase.getInstance(db.graph);
    this.handler = new PingRequestHandler();
  }

  @GET
  @Path("ping")
  @Produces(MediaType.APPLICATION_JSON)
  public Response ping() {
    return getJson(handler.ping());
  }

}
