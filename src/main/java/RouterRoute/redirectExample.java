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

      res.response().setChunked(true);
      res.put("name","Pruthviraj");

      res.redirect("/books").onSuccess(handler->{
        System.out.println("success");
      });

    });


    router.get("/books").handler(res->{
      res.response().setChunked(true);

      String name = res.get("name");

      System.out.println("books in here" + " data is " + name);

      res.redirect("/sports");
    });

    router.get("/sports").handler(res->{
      res.response().setChunked(true);

      System.out.println(res.is("application/json"));
      System.out.println(res.is("text/html"));
      System.out.println("in the sports");
      res.response().end();

    });

    httpServer.requestHandler(router).listen(8080);

  }
}
