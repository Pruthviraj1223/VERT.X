package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class reRoute {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    Router router =  Router.router(vertx);


    router.get("/books").handler(res->{
      res.response().setChunked(true);
      System.out.println("we are in books");
      res.reroute("/sports");

    });


    router.get("/sports").handler(res->{
      res.response().setChunked(true);
      System.out.println("we are in sports");
      res.reroute("/movies");
    });

//
    router.get("/movies").handler(res->{
      res.response().setChunked(true);
      res.response().write("we are in movies\n");
      res.end("we ended");
    });


    httpServer.requestHandler(router).listen(8080);



  }
}
