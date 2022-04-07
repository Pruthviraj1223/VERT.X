package FuturePromisePractice;


import io.vertx.core.Future;
import io.vertx.core.Promise;

public class PromisePractice {
  public static void main(String[] args) {

    Promise<Object> promise1 = Promise.promise();

//    promise1.fail("Hello failure");
    promise1.complete();

    Future<Object> future = promise1.future();

    future
      .onSuccess(result->{
        System.out.println("success");
      })
      .onFailure(result->{
        System.out.println("Fail in hander " + result);
      });


  }
}
