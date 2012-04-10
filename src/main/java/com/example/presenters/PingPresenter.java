package com.example.presenters;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.IOException;
import java.io.StringWriter;

public class PingPresenter extends JsonPresenter {

  public String toJson() {
    StringWriter out = new StringWriter();
    try {
      JsonGenerator g = factory.createJsonGenerator(out);
      JsonNode root = mapper.createObjectNode();

      ((ObjectNode) root).put("message", "indie loaded successfully");

      mapper.writeValue(g, root);
      g.close();
    } catch (IOException e) {
      throw new RuntimeException("Json parsing failed! : " + e.getMessage());
    }
    return out.toString();
  }

}
