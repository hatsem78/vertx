package com.octaviohatsembiller.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.UUID;

public class VerticleA extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(VerticleA.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOGGER.info("start verticle {}", getClass().getName());
    vertx.deployVerticle(VerticleAA.class.getName(),
      new DeploymentOptions()
        .setInstances(1)
        .setConfig(new JsonObject()
          .put("id", UUID.randomUUID().toString())
          .put("name", VerticleAA.class.getSimpleName())
        )
    );
    startPromise.complete();
  }

}
