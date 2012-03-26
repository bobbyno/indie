package com.aurelius.indie.handlers;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PingRequestHandlerTest {
  @Test
  public void shouldSendAcknowledgementMessage() {
    assertEquals("{\"message\":\"freebird loaded successfully\"}", new PingRequestHandler().ping().handle());
  }
}
