package com.aurelius.indie.test.utils;

import org.junit.After;
import org.junit.Before;

public class AutoDbTestBase extends ManualDbTestBase {
  @Before
  public void startTransactionImplicitly() {
    startTransaction();
  }

  @After
  public void rollbackTransactionImplicitly() {
    rollbackTransaction();
  }
}
