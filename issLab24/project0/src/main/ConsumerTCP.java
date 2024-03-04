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
	private int id;
	
	public ConsumerTCP() {
		try {
			port=8011;
			server=new ServerSocket(port);
			id=1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConsumerTCP(int p) {
		try {
			port=p;
			server=new ServerSocket(port);
			id=1;
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
				
				//gestione messaggio conesecuzione relativa funzione
				ConsumerLogic logic=new ConsumerLogic(socket,id);
				logic.run();
				
				//aumento msgid dopo ogni comunicazione
				id++;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
