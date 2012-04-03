package com.graphutils.indie.test.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FileUtils {

  public static String readServerVersion() {
    File server = new File("neo4j-server");
    File[] files = server.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.isDirectory();
      }
    });
    if (files.length == 0) {
      throw new RuntimeException("Could not find the neo4j server directory...have you downloaded it?");
    }
    String fullPath = null;
    try {
      fullPath = String.format("%s/%s", server.getCanonicalPath(), files[0].getName());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fullPath;
  }
}
