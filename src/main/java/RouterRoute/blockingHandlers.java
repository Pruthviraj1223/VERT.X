package RouterRoute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class blockingHandlers {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    Router router =  Router.router(vertx);


    router.route("/cricket/*").blockingHandler(res->{

      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }


      HttpServerResponse httpServerResponse = res.response();
      httpServerResponse.setChunked(true);
      httpServerResponse.write("Helo its in blocking handler");
      httpServerResponse.end();


    });

    httpServer.requestHandler(router).listen(8888);



  }
}
