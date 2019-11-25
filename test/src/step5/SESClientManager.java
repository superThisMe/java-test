package step5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SESClientManager implements Manager {

	Socket socket;
	Object[] response;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public SESClientManager() {

		try {
			socket = new Socket("localhost", 6666);
			System.out.println("서버접속완료");
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

//			oos.writeObject(response);//서버로 오브젝트 배열 보내기 
//			ois.readObject(); //서버에서 처리결과 받기 
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Object send(Object[] obj) {
		Object result = null;

		try {
			oos.writeObject(obj);
			result = ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean insertHuman(Human human) {
		boolean result = false;
		Object[] response = { "insert", human }; // 서버로 내보내는 스트림
		result = (boolean) send(response); // 서버에서 받아오는 스트림
		return result;
	}

	@Override
	public Human findHuman(String jumin) {
		Human result = null;
		response = new Object[] { "find", jumin };
		result = (Human) send(response);
		return result;
	}

	@Override
	public boolean updateHuman(Human human) {
		boolean result = false;
		response = new Object[] { "update", human };
		result = (boolean) send(response);// 서버에서 받아오는 스트림
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		boolean result = false;
		response = new Object[] { "delete", jumin };
		result = (boolean) send(response);// 서버에서 받아오는 스트림
		return result;
	}

	@Override
	public void showAll() {

//		response = new Object[]{"show",null}; 
//		try {
//			@SuppressWarnings("unchecked")
//			ArrayList<Human> al = (ArrayList<Human>)ois.readObject();
//			for (Human h : al) {
//				h.showInfo();
//			}
//			
//		} catch (ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//		}

		response = new Object[] { "show", null };
		ArrayList<Human> al = (ArrayList<Human>) send(response);
		for (Human h : al) {
			h.showInfo();
		}
	}

	@Override
	public ArrayList<Human> getFile() {// 굳이 쓸 필요가 없음요
		return null;
	}

}
