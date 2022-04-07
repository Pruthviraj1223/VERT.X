package EventBus;

import io.vertx.core.*;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RequestResponse{

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(ResponseVerticle.class.getName()).onComplete(result->{
      if(result.succeeded()) {
        vertx.deployVerticle(RequestVerticle.class.getName());
      }
    });

  }

   public static class RequestVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class);
//    static final String ADDRESS = "my.request.address";

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();

      JsonObject jsonObject = new JsonObject().put("Name","Pruthvi").put("Version",1);

      vertx.eventBus().request("add1",jsonObject,handler -> {
            LOG.debug("replied 1 {}" ,handler.result().body().toString());
        }).request("add2","Hola",handler2 -> {
        LOG.debug("replied 2 {}" , handler2.result().body().toString());
      });
    }
  }

   public static class ResponseVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();

      vertx.eventBus().consumer("add1",msg -> {
        LOG.debug("request 1 is {}" , msg.body().toString());
        msg.reply(new JsonArray().add("One").add("TWO").add("Threee"));

      });

      vertx.eventBus().consumer("add2",msg2->{
        LOG.debug("request 2 is {}" , msg2.body().toString());
        msg2.reply("Hola from my world");
      });

    }
  }


}
