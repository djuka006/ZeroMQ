

import java.io.IOException;
import java.util.Random;

import org.zeromq.ZMQ;

public class Ventilator {
  public static void main(String[] args) throws IOException {
    try(ZMQ.Context ctx = ZMQ.context(1);
      ZMQ.Socket sender = ctx.socket(ZMQ.PUSH);
      ZMQ.Socket sink = ctx.socket(ZMQ.PUSH)) {
      sender.bind("tcp://*:5557");
      sink.connect("tcp://localhost:5558");
      
      System.out.println("Press Enter when the workers are ready: ");
      System.in.read();
      System.out.println("Sending tasks to workers...");
      
      sink.send("0");
      Random random = new Random();
      
      int totalMSec = 0;
      for (int taskNumber = 0; taskNumber < 100; ++taskNumber) {
        int workload = random.nextInt(100) + 1;
        totalMSec += workload;
        sender.send(String.format("%d", workload));
      }
      
      System.out.println(String.format("Total expected cost: %d msec", totalMSec));
    }
  }
}
