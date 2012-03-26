package com.aurelius.indie.presenters;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class JsonPresenter {
  protected static ObjectMapper mapper = new ObjectMapper();
  protected static JsonFactory factory = new JsonFactory();

}
