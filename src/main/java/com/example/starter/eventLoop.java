package com.example.starter;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class eventLoop extends AbstractVerticle {


  private static final Logger LOG = LoggerFactory.getLogger(eventLoop.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx( new VertxOptions()
        .setMaxEventLoopExecuteTime(500)
        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
      .setBlockedThreadCheckInterval(1)
      .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
      .setEventLoopPoolSize(100)
    );

    vertx.deployVerticle(eventLoop.class.getName(),new DeploymentOptions().setInstances(200));

  }


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("start {} " + getClass().getName());

    Thread.sleep(1000);

  }
}
