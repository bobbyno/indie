package com.example.presenters;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class ProjectPresenter extends JsonPresenter {

  public String toJson(List projects) {
    StringWriter out = new StringWriter();
    try {
      JsonGenerator g = factory.createJsonGenerator(out);
      JsonNode root = mapper.createObjectNode();

      ArrayNode projectArray = ((ObjectNode) root).putArray("projects");
      for (Object project : projects) {
        projectArray.add(project.toString());
      }
      
      mapper.writeValue(g, root);
      g.close();
    } catch (IOException e) {
      throw new RuntimeException("Json parsing failed! : " + e.getMessage());
    }
    return out.toString();
  }
}
