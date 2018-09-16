
import java.util.Random;

import org.zeromq.ZMQ;

public class Worker {
  public static void main(String[] args) throws InterruptedException {
    try(ZMQ.Context ctx = ZMQ.context(1);
      ZMQ.Socket responder = ctx.socket(ZMQ.REP)) {
      responder.connect("tcp://localhost:5560");
      int id = new Random().nextInt(100);
      while (true) {
        String message = responder.recvStr();
        System.out.println(String.format("Received request: [%s]", message));
        Thread.sleep(new Random().nextInt(4000) + 3000);
        responder.send(String.format("World (from %d)", id));
      }
    }
  }
}
