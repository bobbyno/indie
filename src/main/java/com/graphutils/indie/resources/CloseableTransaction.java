package com.graphutils.indie.resources;

import java.io.Closeable;
import org.neo4j.graphdb.Transaction;
import java.io.IOException;

public class CloseableTransaction implements Closeable {

  private final Transaction transaction;

  public CloseableTransaction(final Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public void close() throws IOException {
    transaction.finish();
  }

  public void commit() {
    transaction.success();
  }

  public void rollback() {
    transaction.failure();
  }
}
