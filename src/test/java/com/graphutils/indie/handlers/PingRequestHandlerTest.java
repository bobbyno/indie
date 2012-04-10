package com.graphutils.indie.handlers;

import junit.framework.Assert;
import org.junit.Test;

public class PingRequestHandlerTest {
  @Test
  public void shouldSendAcknowledgementMessage() {
    Assert.assertEquals("{\"message\":\"indie loaded successfully\"}", new PingRequestHandler().ping().handle());
  }
}
