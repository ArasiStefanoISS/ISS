package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.ConnectionFactory;

public class ServiceInteraction {
	private static ConnectionFactory conFac;
	private static Interaction interaction;
	private static ProtocolType protocol;
	private final static String destination = "servicemath";
	/*2*/  private final static String sender      = "clientjava";
	/*3*/  private final static String hostAddr    = "130.136.113.239";
	/*4*/  private final static int    port        = 8011;
	/*5*/  private final static String msgid       = "dofibo";
	/*6*/  private final static String msgcontent  = "dofibo(6)";
	private static IApplMessage reply;
	
	public static void main(String[] args) {
		try {
			conFac=new ConnectionFactory();
			protocol=ProtocolType.tcp;
			interaction=conFac.createClientSupport(protocol, hostAddr, Integer.toString(port));
			interaction.forward(BasicMsgUtil.buildRequest(sender,msgid,msgcontent,destination));
			reply=interaction.receive();
			System.out.println(reply);
			interaction.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
