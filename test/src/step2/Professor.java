package step2;

import java.io.Serializable;

public class Professor extends Human implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String major;

	public Professor(String name, int age, String jumin, String major) {
		super(name, age, jumin);
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.printf(", [전공 : %s]\n", major);
	}

}
