package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.tcp.TcpConnection;
import unibo.basicomm23.utils.BasicMsgUtil;

public class ConsumerTCP extends Thread{
	private ServerSocket server;
	private int port;
	
	public ConsumerTCP() {
		try {
			port=8011;
			server=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true){
            try {
                System.out.println("Waiting for the client request");
                //creating socket and waiting for client connection
				Socket socket = server.accept();
				
				//passaggio alla libreria Interaction una volta ricevuta la connessione
				Interaction i=new TcpConnection(socket);
				
				//poi si procede come visto a lezione
				IApplMessage msg=i.receive();
				
				//gestione messaggio conesecuzione relativa funzione
				String funz=msg.msgContent();
				System.out.println(funz);
				i.forward(BasicMsgUtil.buildRequest("Consumer","response","ok",msg.msgSender()));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
