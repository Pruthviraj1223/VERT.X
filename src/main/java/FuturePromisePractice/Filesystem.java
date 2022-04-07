package FuturePromisePractice;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

public class Filesystem {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    FileSystem fileSystem = vertx.fileSystem();

    fileSystem.createFile("/home/pruthviraj/daaa2.txt","-w------x");

    Buffer buffer = Buffer.buffer(1000);
    buffer.appendString(" Pruthviraj here");

    OpenOptions openOptions = new OpenOptions();

    fileSystem.open("foo11.txt",openOptions
      .setRead(true)
      .setWrite(true)
        .setAppend(true)
        .setSync(true)
        .setDeleteOnClose(false)
        .setSync(false)
        .setCreate(true)
//        .setCreateNew(true)

      ,result->{
      if(result.succeeded()){

        System.out.println("is append " + openOptions.isRead());

        AsyncFile file = result.result();
        Buffer buffer1 = Buffer.buffer();

        System.out.println("file opened");

        file.write(Buffer.buffer(" Hello from pruthvo"));

//        file.close();

      }
      else{
        System.out.println("file not closed");
      }
    });


    fileSystem.readFile("foo.txt").onSuccess(result->{
//      fileSystem.writeFile("foo.txt",result.getBuffer(0,result.length()).appendString("vedant 123"));

      System.out.println("data is " + result);

    });

    System.out.println("data is here "  + fileSystem.readFileBlocking("foo.txt"));

//    fileSystem.chmod("/home/pruthviraj/keys.txt","rw----rw-");

    fileSystem.copy("foo.txt","Hello.txt");
//
//    fileSystem.deleteRecursive("/home/pruthviraj",);


    vertx.close();


  }
}
