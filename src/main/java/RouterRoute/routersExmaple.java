package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class routersExmaple {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    Router router =  Router.router(vertx);


    router
      .route("/cricket/route")
      .order(1)
      .handler(res->{
      HttpServerResponse response = res.response();

      // Write to the response and end it
     response.write("It is route1\n");

     res.next();


    });

    router
      .route("/cricket/route")
      .order(2)
      .handler(res->{
      HttpServerResponse response = res.response();
      // Write to the response and end it
      response.write("It is route 2\n");

      response.end("it is ended");

    });

    router
      .route("/cricket/route")
      .order(0)
    .handler(res->{
      HttpServerResponse response = res.response();

      response.setChunked(true);

      // Write to the response and end it
      response.write("It is route 3\n");

      res.next();

    });

    httpServer.requestHandler(router).listen(8888);








  }
}
