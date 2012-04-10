package com.graphutils.indie.handlers;

import com.graphutils.indie.presenters.PingPresenter;

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
