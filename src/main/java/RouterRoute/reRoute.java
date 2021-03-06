package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class reRoute {
  public int num =10;
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);


    Router mainrouter = Router.router(vertx);

    Router subrouter1 =  Router.router(vertx);

    Router subrouter2 =  Router.router(vertx);

    mainrouter.mountSubRouter("/pruthviraj",subrouter1);

    mainrouter.mountSubRouter("/vedant",subrouter2);

    subrouter1.get("/books").handler(res->{
      res.response().setChunked(true);
      System.out.println("we are in books");
      res.put("name","Pruthvraj");
      res.reroute("/sports");

    });


    subrouter1.get("/sports").handler(res->{
      res.response().setChunked(true);
      System.out.println("we are in sports");
      String name = res.get("name");
      System.out.println("name is " + name);
      res.reroute("/movies");
    });

//
    subrouter1.get("/movies").handler(res->{
      res.response().setChunked(true);
      String name = res.get("name");
      System.out.println("name is in movies " + name);
      res.response().write("we are in movies\n");
      res.end("we ended");
    });


    subrouter2.get("/home").handler(res->{
      res.response().setChunked(true);

      System.out.println("We are in vedant's home");
      res.reroute("/pg");

    });

    subrouter2.get("/pg").handler(res->{
      res.response().setChunked(true);

      System.out.println("We are in vedant's PG");
      res.response().end("Here in PG we are done");

    });


    httpServer.requestHandler(mainrouter).listen(8080);



  }
}
