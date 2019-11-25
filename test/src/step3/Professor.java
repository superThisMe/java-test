package step3;

import java.io.Serializable;

@SuppressWarnings("serial")
class Professor extends Human implements Serializable {
	private String major;

	public Professor(String name, int age, String jumin, String major) {
		// 부모클래스객체를 먼저 생성되야 상속받은 변수 초기화가 가능 (좀더 공부 필요)
		// 객체 생성시에 쓰는 생성자와 같은 형태 - 생성자(매개변수)
		super(name, age, jumin); // 매개변수 네임, 에이지, 주민을 부모클래스의 생성자에게 넘겨준다
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String m) {
		this.major = m;
	}

	// 오버라이딩 작성
	@Override
	public void showInfo() {
		// 부모클래스의 쇼인포 메소드를 그대로 사용
		super.showInfo();
		System.out.printf(", 전공: %s%n", major);
		// System.out.printf("이름: %s, 나이: %d, 주민번호: %s, 전공:%s%n",getName(), getAge(),
		// get.Jumin(), major);
		// <- getter앞에 super을 안붙여도 이 클래스에 없는 메소드명이기에 자동으로 부모클래스 매소드까지 검색
	}
}
