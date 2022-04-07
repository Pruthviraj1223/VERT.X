package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleAA extends AbstractVerticle {


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    System.out.println("Start = "+ getClass().getName());
    System.out.println("name = " + Thread.currentThread().getName());

    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("STOP = " + getClass().getName());


    stopPromise.complete();
  }
}
