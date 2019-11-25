package step5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SESServerManager implements Manager {
	/* ?배열 new 안해도 되나? */
	ArrayList<Human> al;// 임시저장소
	File f = new File("step5.txt");
	FileOutputStream fos;
	FileInputStream fis;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public SESServerManager() {

		if (f.exists()) {
			al = this.getFile();
		} else {
			al = new ArrayList<Human>();// 배열이 없으면 만든다 ,13라인에 해도 마찬가지 아닌가?
			this.setFile();
		}

	}

	/**
	 * step4에서는 boolean으로 만듬 파일로 저장 fos
	 */
	public void setFile() {

		// 파일스트림생성
		try {
			fos = new FileOutputStream(f);
			// bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.al);// 임시저장소의 배열을 저장시킴
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 해주면 좋지
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Human> getFile() {
		ArrayList<Human> result = null;
		try {
			fis = new FileInputStream(f);// 파일읽어와서
			ois = new ObjectInputStream(fis);// 오브젝트로 변환하고
			// bis = new BufferedInputStream(fis);
			// result = (ArrayList<Human>)bis.read();
			result = (ArrayList<Human>) ois.readObject();// 오브젝트로 변환된 파일 내용을
															// 로컬배열에 담음
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;// 담긴 로컬배열 리턴
	}

	@Override
	public boolean insertHuman(Human human) {
		al = getFile();
		boolean result = false;
		Human h = this.findHuman(human.getJumin());
		if (h == null) {//수정 삭제와 반대로 중복검사 결과 중복값 없어야지 로직을 돌려야함 
			al.add(human);//필드배열에 임시저장 
			setFile();//필드배열을 오브젝트 형태로 파일에 저장 
			result = true;
		} else {
			// 입력하는 사람이 자료에 없으면 바꿀 것도 없으니 그냥 false
		}
		return result;
	}

	@Override
	public Human findHuman(String jumin) {
		al = getFile();
		Human result = null;
		for (Human h : al) {
			if (h.getJumin().equals(jumin)) {
				result = h;
				break;
			}
		}

		return result;
	}

	/* 내가 유일하게 잘한 부분 , indexof를 사용함으로써 반복문으로 하나씩 비교해 찾을 필요 없어짐
	 * set(변경할자료의인덱스, 새자료)의 특성상 findHuman 메소드를 사용할 때는 찾은 자료의 인덱스정보를 
	 * 얻기가 좀 까다로운 것 같다. findHuman을 쓰면서 동시에 indexof를 안쓰고는 어떻게 찾아야할까? 
	 * @see step5.Manager#updateHuman(step5.Human)
	 */
	@Override
	public boolean updateHuman(Human human) {
		al = getFile();
		boolean result = false;
		Human h = this.findHuman(human.getJumin());// 중복검사
		if (h == null) {//중복검사결과 못찾음 
			// 중복없으면 바꿀 것도 없으니까 그냥 false
		} else {
			al.set(al.indexOf(h), human);
			setFile();
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		al = getFile();
		boolean result = false;
		Human h = this.findHuman(jumin);// 중복검사
		if (h == null) {
			// 중복없으면 지울것도 없으니까 그냥 false
		} else {
			al.remove(h);
			setFile();
			result = true;
		}

		return result;
	}

	@Override
	public void showAll() {// 안씀
	}

}
