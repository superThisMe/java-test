package step2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Human implements Serializable {

	private String name;
	private int age;
	private String jumin;
	
	
	public Human(String name, int age, String jumin) {
		this.name = name;
		this.age = age;
		this.jumin = jumin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public void showInfo() {
		System.out.printf("|이름 : %s][나이 : %d][주민번호 : %s]", name, age, jumin);
	}
	
}
