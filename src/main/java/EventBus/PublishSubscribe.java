package EventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;

public class PublishSubscribe {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new Subscriber1()).onComplete(msg->{
      vertx.deployVerticle(new Subscriber2()).onComplete(msg2->{
        vertx.deployVerticle(new Publish()).onComplete(msg3->{

        });
      });
    });

  }

  public static class Publish extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      HashMap<String,String> hashMap = new HashMap<>();
      hashMap.put("12","Pruthvi");
      hashMap.put("31","Vedant");


      JsonObject jsonObject = new JsonObject().put("Name","Pruthvi").put("Version",1);

      vertx.eventBus().publish("add1",jsonObject);
      vertx.eventBus().publish("add2",hashMap.toString());
     startPromise.complete();

    }
  }

  public static class Subscriber1 extends AbstractVerticle{


    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      vertx.eventBus().consumer("add1",msg ->{
        System.out.println("received = " + msg.body().toString());
      });

      vertx.eventBus().consumer("add2",msg ->{
        System.out.println("received 2 = " + msg.body().toString());
      });

      startPromise.complete();
    }
  }


  public static class Subscriber2 extends AbstractVerticle{

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      vertx.eventBus().consumer("add1",msg ->{
        System.out.println("received  = " + msg.body().toString());
      });

      vertx.eventBus().consumer("add2",msg ->{
        System.out.println("received 2 = " + msg.body().toString());
      });


      startPromise.complete();
    }
  }


}
