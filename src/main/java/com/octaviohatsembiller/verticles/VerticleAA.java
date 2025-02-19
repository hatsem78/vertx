package com.octaviohatsembiller.verticles;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleAA extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(VerticleAA.class);

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    LOG.info("Start {}", getClass().getName());
    LOG.info("config: {}", config());
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {
//    LOG.debug("Stop {}", getClass().getName());

    stopPromise.complete();
  }
}
