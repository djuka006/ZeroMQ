
import java.io.IOException;
import org.zeromq.*;
import java.util.Random;
public class worker {

	public static void main(String[] args) {
		try(ZMQ.Context ctx = ZMQ.context(1);
			ZMQ.Socket receiver = ctx.socket(ZMQ.PULL)) {
			receiver.connect("tcp://localhost:3000");
			
			ZMQ.Socket sender = ctx.socket(ZMQ.PUSH);
			sender.connect("tcp://localhost:3001");
			while(true) {
				String message = new String(receiver.recv(0)).trim();
				System.out.println(message);
				try {
					Thread.sleep(Integer.parseInt(message));
					sender.send("");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

}
