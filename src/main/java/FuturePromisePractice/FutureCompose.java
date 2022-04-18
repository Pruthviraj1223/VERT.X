package FuturePromisePractice;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.util.Arrays;
import java.util.HashSet;

public class FutureCompose {
  public static void main(String[] args) {

    Promise<Object> promise2 = Promise.promise();
    promise2.complete();

    Promise<Object> promise3 = Promise.promise();
    promise3.fail("");

    Promise<Object> promise4 = Promise.promise();
    promise4.fail("");


    Future<Object> future2 =  promise2.future();

    future2.compose(o -> promise2.future())
      .compose(o -> promise3.future())
      .compose(o-> promise4.future())
      .onFailure(o->{
        System.out.println("Final future has failed");
      }).onSuccess(o->{
        System.out.println("Final future succeed");
      });


    CompositeFuture.all(Arrays.asList(promise2.future(),promise3.future(),promise4.future()))
      .onComplete(ar -> {
        if(ar.succeeded()){
          System.out.println("Finally it succeeded");
        }else{
          System.out.println("Finally it failed");
        }
      });

//    CompositeFuture.any(Arrays.asList(promise2.future(),promise3.future(),promise4.future()))
//      .onComplete(ar ->{
//
//        if(ar.succeeded()){
//          System.out.println("Finally it succeeded");
//        }else{
//          System.out.println("Finally it failed");
//        }
//
//      });


    CompositeFuture.join(Arrays.asList(promise2.future(),promise3.future(),promise4.future()))
      .onComplete(ar ->{

        if(ar.succeeded()){
          System.out.println("Finally it succeeded");
        }else{
          System.out.println("Finally it failed");
        }

      });



    // BUFFER


    Buffer buffer = Buffer.buffer();
    buffer
      .setString(100,"Hello from this sde");
    buffer.setLong(1,4431);
    buffer.setString(9,"Hello");
    System.out.println("data is " + buffer.getLong(1) + " and " + buffer.getString(2, buffer.length()));

//    all
//      ---------
//      - Handler will get called if and only all futures succeeded and completed.
//      - Handler will get called if and any one future failed and completed.
//
//      join
//      ---------
//      - Handler will only get called only if all futures completed. Status will be success if all futures succeeded or fail if any one failed.

  }
}
