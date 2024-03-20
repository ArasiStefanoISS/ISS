package main.interaction;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class testRequesAfterRequest {
	private static Interaction connSupport;
	
	@BeforeClass
	public static void activateConsumer() {
		CommUtils.outmagenta("activateConsumer");
		new MainEmablersConsumerOnly().configureTheSystem();
		connSupport = ConnectionFactory.createClientSupport(
				          ProtocolType.tcp, "localhost", "8888");
	}
	
	@Test
	public void test() {
		try {
			IApplMessage msg=CommUtils.buildRequest("arasi_stefano", "distance", "10", "consumer");
			connSupport.forward(msg);
			
			
			IApplMessage m =CommUtils.buildRequest("arasi_stefano", "distance", "50", "consumer");
			IApplMessage resp=connSupport.request(m);
			
			
			System.out.println("Primo messaggio");
			System.out.println(resp);
			assertEquals(resp.msgContent(), "ack(50)");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void down() {
		CommUtils.outmagenta("end of  a test "); 
	}

}
