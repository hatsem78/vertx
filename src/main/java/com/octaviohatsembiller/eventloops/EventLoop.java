package com.octaviohatsembiller.eventloops;

import com.octaviohatsembiller.verticles.VerticleA;
import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EventLoop extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventLoop.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx(
      new VertxOptions()
        .setMaxEventLoopExecuteTime(500)
        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
        .setBlockedThreadCheckInterval(1)
        .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
        .setEventLoopPoolSize(2)
    );
    vertx.deployVerticle(EventLoop.class.getName(),
      new DeploymentOptions().setInstances(4)
    );
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    LOGGER.info("Start {}", getClass().getName());
    startPromise.complete();
    // Do not do this inside a verticle
    Thread.sleep(5000);
  }
}
