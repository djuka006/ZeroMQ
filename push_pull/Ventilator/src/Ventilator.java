
import java.io.IOException;
import org.zeromq.*;
import java.util.Random;

public class ventilator {

	public static void main(String[] args) {
		try(ZMQ.Context ctx = ZMQ.context(1);
			      ZMQ.Socket sender = ctx.socket(ZMQ.PUSH);
			      ZMQ.Socket sink = ctx.socket(ZMQ.PUSH)) {
			      sender.bind("tcp://*:3000");
			      sink.connect("tcp://localhost:3001");
			      
			      System.out.println("Press Enter when the workers are ready: ");
			      try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
