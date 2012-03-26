package com.aurelius.indie.resources;

import com.aurelius.indie.exceptions.MissingNodeException;
import com.aurelius.indie.handlers.BooleanRequestHandler;
import com.aurelius.indie.handlers.RequestHandler;
import com.aurelius.indie.handlers.StreamingRequestHandler;
import com.aurelius.indie.handlers.StringRequestHandler;
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

  protected Response getJson(StringRequestHandler handler) {
    try {
      return json(handler.handle());
    } catch (MissingNodeException ex)  {
      return missing();
    } catch (Exception e) {
      e.printStackTrace();
      return error();
    }
  }

  protected StreamingOutput getStream(final StreamingRequestHandler handler) {
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

  protected Response post(RequestHandler handler) {
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

  protected Response post(BooleanRequestHandler handler) {
    return withStatusCode(created(), handler);
  }

  protected Response delete(BooleanRequestHandler handler) {
    return withStatusCode(deleted(), handler);
  }

  protected Response withStatusCode(Response response, BooleanRequestHandler handler) {
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
