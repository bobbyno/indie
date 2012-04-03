package com.graphutils.indie.handlers;

public class PingRequestHandler {

  public StringRequestHandler ping() {
    return new StringRequestHandler() {
      @Override
      public String handle() {
        return new com.graphutils.indie.presenters.PingPresenter().toJson();
      }
    };
  }
}
