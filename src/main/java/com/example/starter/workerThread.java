package com.example.starter;

import eventBusExamples.eventLoop;
import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class workerThread extends AbstractVerticle {


  private static final Logger LOG = LoggerFactory.getLogger(eventLoop.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(2));
    vertx.deployVerticle(workerThread.class.getName(),new DeploymentOptions().setWorker(true).setInstances(10));
  }


  @Override
  public void start(Promise<Void> startPromise)  {

    startPromise.complete();

    LOG.debug("start {} " + getClass().getName());


    vertx.executeBlocking(event -> {
    try {
      Thread.sleep(4000);
      event.complete();
    } catch (InterruptedException e) {
      e.printStackTrace();
      event.fail("Fail");
    }
    },false, result -> {
      if(result.succeeded()){
            LOG.debug("SUCCED" + getClass().getName());
      }else{
        LOG.debug("failed " + getClass().getName());
      }
    });
  }
}
