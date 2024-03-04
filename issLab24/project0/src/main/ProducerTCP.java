package main;

import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.ConnectionFactory;

public class ProducerTCP {
	private String hostIp;
	private int hostPort;
	private int id;
	private String consumer;
	private Interaction interaction;
	
	public ProducerTCP() {
		hostIp="127.0.0.1";
		hostPort=8011;
		id=1;
		consumer="servicemath";
		ConnectionFactory conFac=new ConnectionFactory();
		interaction=conFac.createClientSupport(ProtocolType.tcp,hostIp, Integer.toString(hostPort));
	}
	
	public ProducerTCP(String ind, int por, int ide, Interaction inter, String cons) {
		hostIp=ind;
		hostPort=por;
		id=ide;
		consumer=cons;
		interaction=inter;
	}
	
	public ProducerTCP(String ind, int por, int ide, ProtocolType p, String cons) {
		hostIp=ind;
		hostPort=por;
		id=ide;
		consumer=cons;
		ConnectionFactory conFac=new ConnectionFactory();
		interaction=conFac.createClientSupport(p, hostIp, Integer.toString(hostPort));
	}
	
	public void doJob() {
		try {
			this.getInteraction().forward(BasicMsgUtil.buildRequest("Producer "+this.getId(),"consume","consume(1)",this.getConsumer()));
			IApplMessage msg=this.getInteraction().receive();
			System.out.println("Producer "+ this.getId()+" msg received: "+msg);
			this.getInteraction().close();
		}catch(Exception e) {
			//messaggio errore
		}
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String ip) {
		this.hostIp = ip;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int port) {
		this.hostPort = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Interaction getInteraction() {
		return interaction;
	}

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

	public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

}
