package com.example.starter;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class MainVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) {

  LOG.debug("MAin Start {}" + getClass().getName());

    vertx.deployVerticle("com.example.starter.VerticleA");

    vertx.deployVerticle(new VerticleB());

    vertx.deployVerticle(VerticleN.class.getName(),
      new DeploymentOptions()
      .setInstances(5)
        .setConfig(
          new JsonObject()
          .put("Id ", UUID.randomUUID().toString())
          .put("Name ",VerticleN.class.getName())
        )
    );


    startPromise.complete();

  }
}
