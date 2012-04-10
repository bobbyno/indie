package com.example.handlers;

import com.graphutils.indie.handlers.StringRequestHandler;
import com.example.presenters.PingPresenter;

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
