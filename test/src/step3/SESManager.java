package step3;

import java.io.*;

// 매니져 인터페이스를 구현
import java.util.ArrayList;

class SESManager implements Manager {

	private ArrayList<Human> list = null;

	public SESManager() {
		list = getFile();
	}

	// 주민번호 변수가 중복되지 않도록 객체 삽입
	public boolean insertHuman(Human human) {
		boolean result = false;

		Human find = findHuman(human.getJumin());
		if (find == null) {
			if (list.add(human)) {
				result = setFile(list);
			}
		}

		return result;
	}

	// 매개변수와 같은 이름이면서 같은 값인 변수가 각방의 객체에 있는지 검사
	public Human findHuman(String jumin) {
		Human result = null;
		for (Human a : list) {
			if (a.getJumin().equals(jumin)) {
				result = a;
			} // inner if
		} // for

		return result;
	}

	// 배열안의 객체 하나를 삭제하기 위한 메소드

	public boolean deleteHuman(String jumin) {
		boolean result = false;
		Human find = findHuman(jumin);
		if (find != null) {
			if (list.remove(find)) {
				result = setFile(list);
			}
		}
		return result;
	}

	public void showAll() {

		for (Human a : list) {
			a.showInfo();
		}

	}

	// 객체를 저장한다.
	public boolean setFile(ArrayList<Human> hal) {
		boolean result = true;
		ObjectOutputStream oos = null;
		// 스트림 생성 후 데이터 출력
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File("human.dat"))));
			oos.writeObject(list);

		} catch (FileNotFoundException e) {

			result = false;
		} catch (IOException e2) {

			result = false;
		} catch (Exception e3) {

			result = false;
		} finally {
			try {
				oos.flush();
				oos.close();
			} catch (IOException e) {

				result = false;
			} // inner try
		} // out try
		return result;
	}

	// 객체를 불러온다.
	@SuppressWarnings("unchecked")
	public ArrayList<Human> getFile() {
		ArrayList<Human> result = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File("human.dat"))));

			result = (ArrayList<Human>) ois.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

		} catch (IOException e2) {
			// TODO Auto-generated catch block

		} catch (Exception e3) {
			// TODO Auto-generated catch block

		} finally {
			try {
				if (result == null)
					result = new ArrayList<Human>();

				ois.close();
			} catch (Exception e2) {

			}
		}

		return result;

	}
}
