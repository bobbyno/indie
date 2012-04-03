package com.graphutils.indie.handlers;

import java.io.OutputStream;

public interface StreamingRequestHandler {
  void handle(OutputStream stream);
}
