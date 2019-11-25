package step2;

import java.util.Scanner;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 사용자 화면(User Interface) 클래스
 * 사용자로부터 처리와 관련된 입력을 받고, SESManager 클래스를 통해 처리된 결과를 보여준다.
 * </pre>
 */
public class SESUI {
	private SESManager manager = new SESManager(); // 정의하시오. 요청과 관련된 처리를 하기 위해 생성한 SESManager 클래스의 객체
	private Scanner sc = new Scanner(System.in);// 정의하시오. 키보드로부터 정보를 입력받기 위해 생성한 Scanner 클래스의 객체

	/**
	 * <pre>
	 * SES(Soft Engineer School) 관리 프로그램의 사용자 화면을 구성하고 사용자 입력을 대기한다.
	 * 프로그램은 종료 메뉴를 선택하기 전까지 무한 반복하여 처리 된다.
	 * </pre>
	 */
	public SESUI() {
		while (true) {
			printMainMenu();
			int no = sc.nextInt();
			switch (no) {
			case 1:
				insertHuman();
				break;
			case 2:
				System.out.print("|주민번호 | ");
				String find_jumin = sc.next();
				Human find_h = manager.findHuman(find_jumin);
				// 작성하시오. 객체 형변환 & 분기처리
				// 주민번호를 받아서 검색한다. 없는 경우 검색결과가 없다고 콘솔에 출력한다.
				// 있으면 Human 클래스의 showInfo을 호출한다.
				if (find_h == null) {
					System.out.println("검색결과가 없습니다.");
				} else {
					find_h.showInfo();
				}
				break;
			case 3:
				System.out.print("|주민번호 | ");
				String delete_jumin = sc.next();
				// 작성하시오. bool 타입반환 & 분기처리
				boolean delete_result = manager.deleteHuman(delete_jumin);
				if (delete_result) {
					System.out.println("정상적으로 삭제했습니다.");
				} else {
					System.out.println("삭제할 데이터가 없습니다.");
				}
				break;
			case 4:
				manager.showAll();
				break;
			case 9:
				System.exit(0);
				break;
			default:
			}// switch
		} // while
	}

	/**
	 * <pre>
	 * SES(Soft Engineer School) 관리 프로그램의 등록 메뉴에 대한 처리를 담당한다.
	 * 관리인원(교수, 연수생, 운영진)에 대한 등록을 서브 메뉴로 보여주고, 입력받은 Professor, Trainee, Staff 객체를 SESManager를 통해 등록한다.
	 * </pre>
	 */
	public void insertHuman() {
		while (true) {
			printInsertMenu();
			int no = sc.nextInt();
			switch (no) {
			case 1:
			case 2:
			case 3:
				Human h = makeHuman(no);
				boolean result = manager.insertHuman(h);
				if (result) {
					System.out.println("정상 등록되엇습니다.");
				} else {
					System.out.println("동일한 주민번호가 존재합니다.");
				}
				// 작성하시오. 객체분기
				// 신규 대상을 등록하는 처리를 호출하는 처리
				// makeHuman을 실행
				// 결과 값 bool에 따라 정상등록과 동일주민번호 존재를 분기처리해 콘솔에 출력한다.
				break;
			case 4:
				return;
			default:
			} // switch
		} // while
	}

	/**
	 * SES(Soft Engineer School) 관리 프로그램의 메인 메뉴를 출력한다.
	 */
	public void printMainMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer School 관리 시스템  │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.등록                                                           │");
		System.out.println("│2.찾기                                                           │");
		System.out.println("│3.삭제                                                           │");
		System.out.println("│4.전체출력                                                     │");
		System.out.println("│9.종료                                                           │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

	/**
	 * SES(Soft Engineer School) 관리 프로그램의 관리인원 등록 서브 메뉴를 출력한다.
	 */
	public void printInsertMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│        SES 관리인원 등록                       │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.교수                                                           │");
		System.out.println("│2.연수생                                                        │");
		System.out.println("│3.운영진                                                        │");
		System.out.println("│4.상위메뉴                                                     │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

	/**
	 * 매개변수로 주어진 타입 값에 따라 교수, 연수생, 운영진 정보를 키보드로 입력받아 각각의 객체를 생성하여 반환한다.
	 * 
	 * @param type SES 관리인원 등록 메뉴에서 선택된 번호 값. 1:교수, 2:연수생, 3:운영진
	 * @return SES 관리인원 등록 메뉴에서 선택된 번호에 따라 생성된 Professor, Trainee, Staff 객체
	 */
	public Human makeHuman(int type) {
		Human h = null;
		System.out.print("|이름| ");
		String name = sc.next();
		System.out.print("|나이| ");
		int age = sc.nextInt();
		System.out.print("|주민번호| ");
		String jumin = sc.next();

		switch (type) {
		case 1:
			System.out.print("|전공| ");
			String major = sc.next();
			// 교수 객체 생성
			h = new Professor(name, age, jumin, major);
			break;
		case 2:
			System.out.print("|학번| ");
			String stdNo = sc.next();
			// 학생 객체 생성
			h = new Trainee(name, age, jumin, stdNo);
			break;
		case 3:
			System.out.print("|부서| ");
			String field = sc.next();
			// 교직원 객체 생성
			h = new Staff(name, age, jumin, field);
		}
		return h;
	}

}
