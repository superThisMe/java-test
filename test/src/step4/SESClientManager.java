package step4;
import java.io.*;
import java.net.*;
import java.util.*;


class SESClientManager implements Manager {
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<Human> hal;
	public SESClientManager() {
		 try {
			socket=new Socket("localhost", 5555);
			os=socket.getOutputStream();
			is=socket.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean insertHuman(Human human) {
		boolean result=false;
		result=(boolean) send(new Object[] {"insertHuman",human});
		return result;
	}
	@Override
	public Human findHuman(String jumin) {
		Human h=null;
		h=(Human) send(new Object[] {"findHuman",jumin});
		return h;
	}
	@Override
	public boolean deleteHuman(String jumin) {
		boolean result=false;
		result=(boolean) send(new Object[] {"deleteHuman",jumin});
		return result;
	}
	@Override
	public void showAll() {
		ArrayList<Human> al=getFile();
		if(al==null)System.out.println("1");
		else{
		for (Human human : al) {
			human.showInfo();
		}
		}
	}
	@Override
	public ArrayList<Human> getFile() {
		ArrayList<Human> al=null;
		al=(ArrayList<Human>) send(new Object[] {"getFile"});		
		return al;
	}
	public Object send(Object[] obj){
		Object ob=null;
		try {
			oos=new ObjectOutputStream(os);
			oos.writeObject(obj);
			ois=new ObjectInputStream(is);
			ob=ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ob;
	}
}














