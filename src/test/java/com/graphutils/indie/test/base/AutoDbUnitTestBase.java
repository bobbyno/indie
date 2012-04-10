package com.graphutils.indie.test.base;

import org.junit.After;
import org.junit.Before;

public class AutoDbUnitTestBase extends ManualDbUnitTestBase {
  @Before
  public void startTransactionImplicitly() {
    startTransaction();
  }

  @After
  public void rollbackTransactionImplicitly() {
    rollbackTransaction();
  }
}
