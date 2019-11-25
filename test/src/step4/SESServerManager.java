package step4;
import java.io.*;
import java.util.*;
/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 업무로직을 관리하는 클래스
 * 주요 기능으로는 다음과 같다.
 * 1. 신규 관리인원 등록
 * 2. 등록인원 검색
 * 3. 등록인원 삭제
 * 4. 전체 등록인원 출력
 * </pre>
 * */
public class SESServerManager implements Manager{

	private ArrayList<Human> al;
	private FileInputStream fis;//파일을 읽기위한
	private FileOutputStream fos;//파일을 쓰기위한
	private ObjectInputStream ois;//객체를 읽기위한
	private ObjectOutputStream oos;//객체를 쓰기위한

	public SESServerManager(){
		if(new File("human.dat").exists()){
			al = this.getFile();
		}else{
			al = new ArrayList<Human>();
		}
	}


	public boolean setFile(ArrayList<Human> hal){

		boolean result = false;
				try {
					oos=new ObjectOutputStream(fos=new FileOutputStream("human.dat"));
					oos.writeObject(hal);

				} catch (Exception e) {
				e.printStackTrace();
				}
		return result;
	}

	public ArrayList<Human> getFile(){

		ArrayList<Human> hal = null;
		try {
			ois=new ObjectInputStream(fis=new FileInputStream("human.dat"));
			hal=(ArrayList<Human>) ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hal;
	}


	@Override
	public boolean insertHuman(Human human) {
		if(new File("human.dat").exists()){
			al = this.getFile();
		}else{
			al = new ArrayList<Human>();
		}
		boolean result = true;
		for(int i=0;i<al.size();i++){
			if(al.get(i).getJumin().equals(human.getJumin())){
				result=false;
			}
		}
		if(result){
			al.add(human);
			setFile(al);
		}
		return result;
	}


	@Override
	public Human findHuman(String jumin) {

		if(new File("human.dat").exists()){
			al = this.getFile();
		}else{
			al = new ArrayList<Human>();
		}
		Human h=null;
		for (int i = 0; i < al.size(); i++) {
			if(al.get(i).getJumin().equals(jumin)){
				h=al.get(i);
			}
		}

		return h;
	}


	@Override
	public boolean deleteHuman(String jumin) {
		if(new File("human.dat").exists()){
			al = this.getFile();
		}else{
			al = new ArrayList<Human>();
		}
		boolean result=false;
		for (int i = 0; i < al.size(); i++) {
			if(al.get(i).getJumin().equals(jumin)){
				al.remove(i);
				result=true;
			}
		}
		setFile(al);
		return result;
	}


	@Override
	public void showAll() {

	}
}