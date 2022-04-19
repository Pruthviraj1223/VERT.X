package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class mountRouter {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);


    Router mainrouter = Router.router(vertx);

    Router subrouter1 =  Router.router(vertx);

    Router subrouter2 =  Router.router(vertx);

    mainrouter.mountSubRouter("/pruthviraj/",subrouter1);

    mainrouter.mountSubRouter("/vedant",subrouter2);

    subrouter1.get("/books").handler(res->{
      res.response().setChunked(true);
      res.response().end("we are in books");
    });


    subrouter1.get("/sports").handler(res->{
      res.response().setChunked(true);
      res.response().end("we are in sports");
    });

//
    subrouter1.get("/movies").handler(res->{
      res.response().setChunked(true);
      res.response().end("we are in movies");
    });


    subrouter2.get("/home").handler(res->{
      res.response().setChunked(true);

      res.response().end("We are in vedant's home");


    });

    subrouter2.get("/pg").handler(res->{
      res.response().setChunked(true);
      res.response().end("Here in PG we are done");

    });

httpServer.requestHandler(mainrouter).listen(8080);

  }
}
