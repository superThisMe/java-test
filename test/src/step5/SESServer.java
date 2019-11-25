package step5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SESServer {

	public static void main(String[] args) {
		Manager sm = new SESServerManager();

		try {
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("서버접속대기");
			Socket socket = ss.accept();
			System.out.println("클라이언트 접속완료");

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			while (socket.isConnected()) {

				Object[] responsed = (Object[]) ois.readObject(); // cm에서 받음
				String key = (String) responsed[0];
				Object param = (Object) responsed[1];

				switch (key) {
				case "insert":
					Human h = (Human) param;
					boolean result = sm.insertHuman(h); // sm메소드 사용
					oos.writeObject(result); // cm로 내보냄
					break;
				case "delete":
					String jumin = (String) param;
					boolean result2 = (boolean) sm.deleteHuman(jumin);
					oos.writeObject(result2);
					break;
				case "find":
					String jumin2 = (String) param;
					Human result3 = (Human) sm.findHuman(jumin2);
					oos.writeObject(result3);
					break;
				case "update":
					Human h2 = (Human) param;
					boolean result4 = (boolean) sm.updateHuman(h2);
					oos.writeObject(result4);
					break;
				case "show":
					ArrayList<Human> al = (ArrayList<Human>) sm.getFile(); // 겟파일한 배열리턴
					oos.writeObject(al);
					break;
				}

			}
			ss.close();

		} catch (Exception e) {
			System.out.println("접속이 종료되었습니다.");
			e.printStackTrace();
		}

	}

}
