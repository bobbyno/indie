package com.graphutils.indie.utils;

public class Timer {
  private long start;
  private double duration;

  private Timer() {}

  public static Timer init() {
    Timer logger = new Timer();
    logger.start();
    return logger;
  }

  public void start() {
    start = System.currentTimeMillis();
  }

  public void finish(String message) {
    duration = (System.currentTimeMillis() - start) / 1000.0;
    System.out.println("**TIME [" + duration + "s] " + message);
  }

  public double duration() {
    return duration;
  }
}
