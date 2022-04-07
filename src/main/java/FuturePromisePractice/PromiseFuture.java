package FuturePromisePractice;


import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class  A{
  int id;

  public void display(){

  }

}

public class PromiseFuture {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

//
    Promise<Object> promise1 = Promise.promise();

    vertx.setTimer(3000,reslt->{
      System.out.println("inside function");
     promise1.complete("It's completed");
      System.out.println("2ND LINE");
    });


    Future<Object> future = promise1.future();

    ArrayList<Integer> arrayList =  new ArrayList<Integer>();
    arrayList.add(12);
    arrayList.add(13);
    arrayList.add(22);

    HashMap<String,String> hashMap = new HashMap<>();
    hashMap.put("name","Pruthvi");
    JsonObject jsonObject = new JsonObject().put("Name",hashMap);
    jsonObject.put("List",arrayList);
    jsonObject.put("bool",true);
//    jsonObject.put("class",new A());

    System.out.println("json = " + jsonObject);

    JsonArray jsonArray = new JsonArray();
    jsonArray.add("1");
    jsonArray.add(arrayList);
    jsonArray.add(hashMap);
    jsonArray.add(true);
//    jsonArray.add(new A());

    HashSet<Integer> hashSet = new HashSet<>();
    hashSet.add(12);

    jsonObject.put("sds",hashSet);


    System.out.println("json array " + jsonObject);


    future
      .map(string->{
        System.out.println("Inside map method");
        return new JsonObject().put("Name","Pruthvi");
      })
      .map(jsonObject2 -> {
        return new JsonArray().add(jsonObject2).add(new ArrayList<>().add("Pruthvi"));
      })
      .onSuccess(result->{
        System.out.println("Result : " + result);
      })
      .onFailure(result->{
        System.out.println("Fail in handler " + result);
      });

  }
}
