package step3;

import java.util.ArrayList;

class SESTest {
	public static void main(String[] args) {
		ArrayList<Human> list = new ArrayList<Human>();
		list.add(new Professor("최승호", 30, "11-11", "자바"));
		list.add(new Trainee("홍길동", 20, "11-22", "01"));
		list.add(new Staff("고인혁", 25, "11-33", "학사관리"));
		for (Human human : list) {
			System.out.println("이름: " + human.getName());
			System.out.println("나이: " + human.getAge());
			System.out.println("주민번호: " + human.getJumin());
			if (human instanceof Professor) {
				System.out.println("전공: " + ((Professor) human).getMajor());
			} else if (human instanceof Trainee) {
				System.out.println("학번: " + ((Trainee) human).getStdNo());
			} else if (human instanceof Staff) {
				System.out.println("부서: " + ((Staff) human).getField());
			}

		}
	}
}
