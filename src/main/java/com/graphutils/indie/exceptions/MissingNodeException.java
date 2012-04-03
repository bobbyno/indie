package com.graphutils.indie.exceptions;

public class MissingNodeException extends RuntimeException {

  public MissingNodeException(String refType, String refId) {
    super(String.format("Could not find %s:%s", refType, refId));
  }
}
