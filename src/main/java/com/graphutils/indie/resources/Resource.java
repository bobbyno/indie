package com.graphutils.indie.resources;

import com.sun.jersey.spi.CloseableService;
import org.neo4j.server.database.Database;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

import static javax.ws.rs.core.Response.Status.*;

public class Resource {

  protected Database db;
  protected CloseableService closeableService;

  protected Response getJson(com.graphutils.indie.handlers.StringRequestHandler handler) {
    try {
      return json(handler.handle());
    } catch (com.graphutils.indie.exceptions.MissingNodeException ex)  {
      return missing();
    } catch (Exception e) {
      e.printStackTrace();
      return error();
    }
  }

  protected StreamingOutput getStream(final com.graphutils.indie.handlers.StreamingRequestHandler handler) {
    return new StreamingOutput() {
      public void write(OutputStream output) throws IOException, WebApplicationException {
        try {
          handler.handle(output);
        } catch (Exception e) {
          e.printStackTrace();
          throw new WebApplicationException(e);
        } finally {
          output.close();
        }
      }
    };
  }

  protected Response post(com.graphutils.indie.handlers.RequestHandler handler) {
    final CloseableTransaction transaction = new CloseableTransaction(db.graph.beginTx());
    closeableService.add(transaction);
    try {
      handler.handle();
      transaction.commit();
      return created();
    } catch (Exception e) {
      e.printStackTrace();
      transaction.rollback();
      return error();
    }
  }

  protected Response post(com.graphutils.indie.handlers.BooleanRequestHandler handler) {
    return withStatusCode(created(), handler);
  }

  protected Response delete(com.graphutils.indie.handlers.BooleanRequestHandler handler) {
    return withStatusCode(deleted(), handler);
  }

  protected Response withStatusCode(Response response, com.graphutils.indie.handlers.BooleanRequestHandler handler) {
    final CloseableTransaction transaction = new CloseableTransaction(db.graph.beginTx());
    closeableService.add(transaction);
    try {
      if (!handler.handle()) {
        transaction.rollback();
        return error();
      }
      transaction.commit();
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      transaction.rollback();
      return error();
    }
  }

  private Response deleted() {
    return Response.status(NO_CONTENT).build();
  }

  private Response missing() {
    return Response.status(NOT_FOUND).build();
  }

  private Response created() {
    return Response.status(CREATED).build();
  }

  private Response error() {
    return Response.serverError().build();
  }

  private Response json(String json) {
    return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
  }
}
