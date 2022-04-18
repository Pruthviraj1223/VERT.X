package HtttpServerExample;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.apache.logging.log4j.core.appender.routing.Route;

public class httpExample  {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpServerOptions httpServerOptions = new HttpServerOptions().setMaxHeaderSize(100000);

    HttpServer httpServer =  vertx.createHttpServer(httpServerOptions);

    httpServer.requestHandler(res->{
      System.out.println("request is here");

      HttpServerResponse httpServerResponse = res.response();

      httpServerResponse.setChunked(true);

      System.out.println("status code " + httpServerResponse.getStatusCode());
//      httpServerResponse.sendFile("/home/pruthviraj/file.txt");


      httpServerResponse.putHeader("content-type", "text/plain");
      //multipart/form-data;

      System.out.println("header " +res.getHeader("Host"));
      System.out.println("Type of method = " + res.method());

//      httpServerResponse.send("GUYS ");
      System.out.println("query " + res.localAddress());

      httpServerResponse.write("Hello");
      httpServerResponse.write("World");
      httpServerResponse.end("bybye");


    }).listen(8888);




  }
}
