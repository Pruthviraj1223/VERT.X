package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws InterruptedException {
    System.out.println("Start = " + getClass().getName());

    Thread.sleep(4000);

    vertx.deployVerticle(new VerticleAA(), whenDeployed -> {
      System.out.println("deployed = " + getClass().getName());
      vertx.undeploy(whenDeployed.result());
    });


    vertx.deployVerticle(new VerticleAB());

    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {

  }
}
