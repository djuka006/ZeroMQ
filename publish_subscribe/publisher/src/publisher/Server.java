package publisher;


import java.util.Random;

import org.zeromq.ZMQ;

public class Server {
  
  public static void main(String[] args) {
    try(ZMQ.Context ctx = ZMQ.context(1);
      ZMQ.Socket publisher = ctx.socket(ZMQ.PUB)) {
      publisher.bind("tcp://localhost:5556");
      
      Random random = new Random();
      while (true) {
        int id = random.nextInt(100000);
        int data = random.nextInt(500);
        publisher.send(String.format("%05d %d", id, data));
      }
    }
  }
}
