package main;

import java.net.Socket;

import unibo.basicomm23.tcp.TcpConnection;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.utils.BasicMsgUtil;

public class ConsumerLogic extends Thread{
	private Socket socket;
	private Interaction inter;
	private int id;
	
	private ConsumerLogic(Socket soc,int i) {
		try {
			this.socket=soc;
			this.inter=new TcpConnection(this.socket);
			this.id=i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		IApplMessage msg;
		try {
			//estrazione messaggio
			msg = this.getInter().receive();
			System.out.println(msg);
			
			//risposta simil-ack
			if (msg.msgContent().equals("consume")) {
				this.getInter().forward(BasicMsgUtil.buildRequest(msg.msgReceiver(),Integer.toString(id),"ok",msg.msgSender()));
			}
			else {
				this.getInter().forward(BasicMsgUtil.buildRequest(msg.msgReceiver(),Integer.toString(id),"Bad Request",msg.msgSender()));
			}
			
			//chiusura connessione
			this.getInter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Interaction getInter() {
		return inter;
	}

	public void setInter(Interaction inter) {
		this.inter = inter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
