package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RoutersExample {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    Router router =  Router.router(vertx);

//    Route route = router.route().method(HttpMethod.POST).method(HttpMethod.PUT);

    router.route().handler(BodyHandler.create());

    router.routeWithRegex(".*cricket").handler(res->{
      HttpServerResponse response = res.response();
      response.setChunked(true);

      // Write to the response and end it
      response.end("You are in cricket world!");
    });


    router.post("/movie").handler(handler->{
      HttpServerResponse response = handler.response();
      response.setChunked(true);

      // Write to the response and end it

//     response.write("data is " +  handler.getBodyAsJson() + "\n");
//     response.write("data is " + handler.getBodyAsString() + "\n");
     response.write("data is " + handler.getBodyAsJsonArray().getJsonObject(0) + "\n");

      response.end("Hello World from MOVIE!");

    });

    router.
      route(HttpMethod.POST,"/books/:name/:id/").
      handler(ctx -> {

      HttpServerResponse response = ctx.response();



      response.setChunked(true);

      String name = ctx.pathParam("params0");
      String id = ctx.pathParam("params1");

      response.write("name of books is " + name + " and id is " + id);

      // Call the next matching route after a 5 second delay
      response.end();
    });

    router.put("/sports").handler(ctx -> {

      HttpServerResponse response = ctx.response();
      response.setChunked(true);
      response.write("It's Sports \n");

      response.end();

    });

    router.delete("/music").handler(ctx -> {

      HttpServerResponse response = ctx.response();
      response.setChunked(true);
      response.write("It's Music");


      response.end();
    });


    httpServer.requestHandler(router).listen(8888);

    // https://www.httpwatch.com/httpgallery/chunked/

  }
}
