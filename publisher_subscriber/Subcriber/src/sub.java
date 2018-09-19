
import org.zeromq.*;
import java.util.Random;
public class sub {

	public static void main(String[] args) {
		try(ZMQ.Context ctx = ZMQ.context(1);
			      ZMQ.Socket sub = ctx.socket(ZMQ.SUB)){
			sub.connect("tcp://localhost:3000");
			Random random = new Random();
			int id = random.nextInt(100000);
			String s = id+"";
			sub.subscribe(s.getBytes());
			String address = sub.recvStr();
			String content = sub.recvStr();
			System.out.println(address + ": " + content);
		}
	}
}
