import java.util.Random;

import org.zeromq.ZMQ;
public class worker {

	public static void main(String[] args) throws InterruptedException {
	    try(ZMQ.Context ctx = ZMQ.context(1);
	    	      ZMQ.Socket responder = ctx.socket(ZMQ.REP)) {
	    	      responder.connect("tcp://localhost:3000");
	    	      int id = 1;
	    	      while (true) {
	    	        String message = responder.recvStr();
	    	        System.out.println(String.format("Received request: [%s]", message));
	    	        Thread.sleep(new Random().nextInt(4000) + 3000);
	    	        responder.send(String.format("World (from %d)", id));
	    	      }
	    	    }
	    	  }

}
