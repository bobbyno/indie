package com.aurelius.indie.handlers;

import com.aurelius.indie.presenters.PingPresenter;

public class PingRequestHandler {

  public StringRequestHandler ping() {
    return new StringRequestHandler() {
      @Override
      public String handle() {
        return new PingPresenter().toJson();
      }
    };
  }
}
