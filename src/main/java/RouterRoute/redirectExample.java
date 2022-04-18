package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class redirectExample {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    Router router =  Router.router(vertx);

    router.get("/movie").handler(res -> {

      HttpServerResponse response = res.response();
      response.setChunked(true);

      //response.write("hello movie");

      res.redirect("/books").onSuccess(handler->{
        System.out.println("success");
      }).onComplete(req->{
        res.redirect("/kiterunner");
      });

    });

    router.get("/books").handler(res->{
      res.response().setChunked(true);
      System.out.println("books in here");

      res.response().write("Books!!! You are redirected here!!!!!");

      res.end();
    });

    router.get("/sports").handler(res->{
      res.response().setChunked(true);

      System.out.println(res.is("application/json"));
      System.out.println(res.is("text/html"));
      res.response().end();

    });

    httpServer.requestHandler(router).listen(8080);

  }
}
