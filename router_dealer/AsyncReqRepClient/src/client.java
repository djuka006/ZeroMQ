import java.util.Random;

import org.zeromq.ZMQ;
public class client {

	public static void main(String[] args) { 
		try(ZMQ.Context ctx = ZMQ.context(1);
  	      ZMQ.Socket request = ctx.socket(ZMQ.REQ)) {
			int id = 1;
			request.connect("tcp://localhost:3001");
			int requsetNumber = 0;
			for(; requsetNumber < 10; ++requsetNumber) {
				request.send(String.format("Zdravo (od %d)", id));
				String message = request.recvStr();
				System.out.println("Primio odgovor " + requsetNumber + "[" + message + "]");
			}
		}
	}
}
