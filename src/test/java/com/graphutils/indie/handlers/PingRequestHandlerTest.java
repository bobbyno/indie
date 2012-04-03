package com.graphutils.indie.handlers;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PingRequestHandlerTest {
  @Test
  public void shouldSendAcknowledgementMessage() {
    Assert.assertEquals("{\"message\":\"indie loaded successfully\"}", new com.graphutils.indie.handlers.PingRequestHandler().ping().handle());
  }
}
