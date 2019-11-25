package step3;

import java.util.Scanner;

/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 사용자 화면(User Interface) 클래스
 * 사용자로부터 처리와 관련된 입력을 받고, SESManager 클래스를 통해 처리된 결과를 보여준다.
 * </pre>
 */
class SESUI {
	private SESManager manager = new SESManager(); // 요청과 관련된 처리를 하기 위해 생성한 SESManager 클래스의 객체
	private Scanner sc = new Scanner(System.in); // 키보드로부터 정보를 입력받기 위해 생성한 Scanner 클래스의 객체

	/**
	 * <pre>
	 * SES(Soft Engineer School) 관리 프로그램의 사용자 화면을 구성하고 사용자 입력을 대기한다.
	 * 프로그램은 종료 메뉴를 선택하기 전까지 무한 반복하여 처리 된다.
	 * </pre>
	 */
	public SESUI() {
		while (true) {
			printMainMenu();
			int no = 0;
			String jumin = null;

			try {
				no = (int) Integer.parseInt(sc.next());
			} catch (NumberFormatException numberError) {
				System.out.println("입력 오류! 다시 입력하세요.");
				continue;
			}

			switch (no) {
			case 1:
				this.insertHuman();
				break;
			case 2:
				System.out.print("│주민번호 입력│ ");
				jumin = sc.next();
				Human result = manager.findHuman(jumin);
				if (result == null) {
					System.out.println("│!!검색결과 : 데이터에 없는 주민번호!!│");
				} else {
					result.showInfo();
				}
				break;
			case 3:
				System.out.print("│주민번호 입력│ ");
				jumin = sc.next();
				boolean answer = manager.deleteHuman(jumin);
				if (answer == true) {
					System.out.println("│삭제 완료│");
				} else if (answer == false) {
					System.out.println("│!!삭제실패!!│");
				}
				break;
			case 4:
				manager.showAll();
				break;
			case 9:
				return;
			default:
				System.out.printf("메뉴입력오류!%n%n");

			}
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
			int no = 0;
			Human h = null;
			try {
				no = (int) Integer.parseInt(sc.next());
			} catch (NumberFormatException numberError) {
				System.out.println("입력 오류! 다시 입력하세요.");
				continue;
			}
			switch (no) {
			case 1:
				h = makeHuman(no);
				break;
			case 2:
				h = makeHuman(no);
				break;
			case 3:
				h = makeHuman(no);
				break;
			case 4:
				return;
			default:
				System.out.println("메뉴번호 입력오류!!");
				insertHuman();
			}
			// 메뉴번호 입력오류 시 널포인트 오류를 피하기 위한 코드
			if (h == null) {
				break;
			}
			boolean answer = manager.insertHuman(h);
			if (answer == true) {
				System.out.println("정상등록되었습니다.");
			} else if (answer == false) {
				System.out.println("동일한 주민번호가 존재합니다.");
			}

		} // while
	}

	// 객체 삽입 매소드
	public Human makeHuman(int type) {
		Human result = null;

		exit: while (true) {
			int a = 0;
			System.out.print("│이름│ ");
			String n = sc.next();
			try {
				System.out.print("│나이│ ");
				a = (int) Integer.parseInt(sc.next());
			} catch (NumberFormatException numberError) {
				System.out.println("입력 오류! 다시 입력하세요.");
				break;
			} // try catch

			System.out.print("│주민번호│ ");
			String j = sc.next();
			switch (type) {
			case 1:
				System.out.print("│전공│ ");
				String m = sc.next();
				result = new Professor(n, a, j, m);
				break exit;
			case 2:
				System.out.print("│학번│ ");
				String s = sc.next();
				result = new Trainee(n, a, j, s);
				break exit;
			case 3:
				System.out.print("│부서│ ");
				String f = sc.next();
				result = new Staff(n, a, j, f);
				break exit;
			} // switch

		} // while

		return result;
	}

	/**
	 * SES(Soft Engineer School) 관리 프로그램의 메인 메뉴를 출력한다.
	 */
	public void printMainMenu() {
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│ Soft Engineer School 관리 시스템       │");
		System.out.println("└────────────────────────────────┘");
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│1.등록                                                                │");
		System.out.println("│2.찾기                                                                │");
		System.out.println("│3.삭제                                                                │");
		System.out.println("│4.전체출력                                                          │");
		System.out.println("│9.종료                                                                │");
		System.out.println("└────────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

	/**
	 * SES(Soft Engineer School) 관리 프로그램의 관리인원 등록 서브 메뉴를 출력한다.
	 */
	public void printInsertMenu() {
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│        SES 관리인원 등록                           │");
		System.out.println("└────────────────────────────────┘");
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│1.교수                                                                │");
		System.out.println("│2.연수생                                                             │");
		System.out.println("│3.운영진                                                             │");
		System.out.println("│4.상위메뉴                                                          │");
		System.out.println("└────────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

}
