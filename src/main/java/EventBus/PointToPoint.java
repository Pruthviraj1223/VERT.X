package EventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;

public class PointToPoint {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(Receiver.class.getName()).onComplete(msg->{

    if(msg.succeeded()){
        vertx.deployVerticle(Sender.class.getName());
    }else{
      System.out.println("fail");
    }


    });
  }


   public static class Sender extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      vertx.eventBus().send("address","Sending a message....");
      startPromise.complete();
    }
  }


   public static class Receiver extends AbstractVerticle{

    @Override
    public void start(Promise<Void> startPromise) throws Exception {


      vertx.eventBus().consumer("address", message -> {
        System.out.println("Received " + message.body());
      });

      startPromise.complete();
    }
  }
}
