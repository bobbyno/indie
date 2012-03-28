package com.aurelius.indie.test.functional;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import static com.sun.jersey.api.client.Client.create;

public class Client {

  private static final String BASE_URI = "http://localhost:7474/ext/";

  public ClientResponse getJson(String resource) {
    return get(resource, MediaType.APPLICATION_JSON);
  }

  public ClientResponse post(String resource) {
    String url = url("POST", BASE_URI + resource);
    return create().resource(url).post(ClientResponse.class);
  }

  public ClientResponse delete(String resource) {
    String url = url("DELETE", BASE_URI + resource);
    return create().resource(url).delete(ClientResponse.class);
  }

  public ClientResponse get(String resource, String format) {
    String url = url("GET", BASE_URI + resource);
    return create().resource(url).accept(format).get(ClientResponse.class);
  }

  public ClientResponse post(String resource, MultivaluedMap<String, String> formData) {
    String url = url("POST", BASE_URI + resource);
    return create().resource(url).type("application/x-www-form-urlencoded").post(ClientResponse.class, formData);
  }

  public ClientResponse delete(String resource, MultivaluedMap<String, String> formData) {
    String url = url("DELETE", BASE_URI + resource);
    return create().resource(url).type("application/x-www-form-urlencoded").delete(ClientResponse.class, formData);
  }

  private String url(String op, String url) {
    System.out.println(String.format("%s %s", op, url));
    return url;
  }
}
