package step4;
import java.io.*;
import java.net.*;
import java.util.*;


class SESServer {
	
	public static void main(String[] args) {
		SESServerManager sm = new SESServerManager();	
		try{
			ServerSocket ssocket = new ServerSocket(5555);
			System.out.println("server standby!!!");
			Socket socket = ssocket.accept();	
			
			while(socket.isConnected()){
				ObjectInputStream nois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream noos = new ObjectOutputStream(socket.getOutputStream());
				
				Object[] obj=(Object[])nois.readObject();
				boolean result=false;
				String key=(String) obj[0];
				switch(key){
				case "insertHuman":
					Human h=(Human) obj[1];	
					result=sm.insertHuman(h);
					noos.writeObject(result);
					break;
				case "findHuman":
					h=sm.findHuman((String)obj[1]);
					noos.writeObject(h);
					break;
				case "deleteHuman":
					result=sm.deleteHuman((String)obj[1]);
					noos.writeObject(result);
					break;
				case "getFile":
					ArrayList<Human> al= sm.getFile();
					noos.writeObject(al);
					break;
				}
			}
			ssocket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
