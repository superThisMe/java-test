package step2;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 업무로직을 관리하는 클래스
 * 주요 기능으로는 다음과 같다.
 * 1. 신규 관리인원 등록
 * 2. 등록인원 검색
 * 3. 등록인원 삭제
 * 4. 전체 등록인원 출력
 * </pre>
 */
public class SESManager implements Manager {
	// 정의하시오. 관리인원(교수, 연수생, 운영진) 객체를 저장할 저장 공간. 연관배열 ArrayList 사용
	private ArrayList<Human> humanlist;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private FileInputStream fis;

	/**
	 * 주어진 size의 초기값으로 새로운 SESManager 클래스의 객체를 생성한다. *
	 */
	public SESManager() {
		try {
			if (new File("human.dat").exists()) {
				humanlist = getFile();
			} else {
				humanlist = new ArrayList<Human>();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 새로운 Human 객체를 등록한다.
	 * 등록된 Human 객체 정보 중 주민번호는 중복될 수 없으며, 이미 등록된 주민번호의 Human 정보는 등록할 수 없다.
	 * </pre>
	 * 
	 * @param human 등록할 Professor, Trainee, Staff 클래스의 객체
	 * @return 등록 성공 시 true를, 실패(이미 등록된 주민번호 중복 등록) 시 false를 반환한다.
	 */
	public boolean insertHuman(Human human) {
		boolean result = true;
		if (new File("human.dat").exists()) {
			humanlist = getFile();
			for (Human h : humanlist) {
				if (h.getJumin().equals(human.getJumin())) {
					result = false;
					break;
				}

			}
			if (result) {
				humanlist.add(human);
				setFile(humanlist);
			}
		}
		return result;
	}

	/**
	 * 등록된 Human 객체를 검색한다.
	 * 
	 * @param jumin 검색할 Human의 주민번호
	 * @return Human ArrayList에 등록된 객체들 중 파라메터로 전달된 주민번호와 일치되는 Human 객체, 검색 결과가 없을 시
	 *         null을 반환한다.
	 */
	public Human findHuman(String jumin) {
		Human find_human = null;
		for (Human human : humanlist) {
			if (human.getJumin().equals(jumin)) {
				find_human = human;
				break;
			}
		}
		return find_human;

	}

	/**
	 * 등록된 Human 객체를 삭제한다.
	 * 
	 * @param jumin 삭제할 Human의 주민번호
	 * @return 주어진 주민번호와 일치되는 Human 객체의 삭제 결과, ArrayList에 등록된 객체들 중 파라메터로 전달된 주민번호와
	 *         일치되는 Human 객체가 발견되어 삭제를 성공하면 true를 그렇지 않으면 false를 반환
	 */
	public boolean deleteHuman(String jumin) {
		boolean result = false;
		for (int i = 0; i < humanlist.size(); i++) {
			if (humanlist.get(i).getJumin().equals(jumin)) {
				humanlist.remove(i);
				setFile(humanlist);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 등록된 모든 Human 객체 정보를 출력한다.
	 */
	public void showAll() {
		for (Human human : humanlist) {
			human.showInfo();
		}
	}

	public boolean setFile(ArrayList<Human> hal) {
		boolean result = false;
		try {
			fos = new FileOutputStream("human.dat"); // 정의하시오. human.dat 파일을 오브젝트 스트림으로 부른다.
			oos = new ObjectOutputStream(fos); // 정의하시오. 오브젝트를 아웃풋하는 오브젝트 아웃풋 스트림에 건내준다.
			if (hal == null) {
				hal = new ArrayList<Human>();
			}
			// 정의하시오. oos의 메소드에 hal을 건내준다. 이는 결국 새파일시스템으로 저장된다.
			oos.writeObject(hal);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Human> getFile() {
		ArrayList<Human> hal = null;
		try {
			fis = new FileInputStream("human.dat"); // 정의하시오. 이번에는 파일 인풋 스트림을 정의
			ois = new ObjectInputStream(fis); // 정의하시오. fis를 오브젝트를 인풋해주는 스트림으로 연결한다.
			hal = (ArrayList<Human>) ois.readObject(); // 인풋한다.
		} catch (Exception e) {
			// e.printStackTrace();
			hal = new ArrayList<Human>();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hal;
	}
}
