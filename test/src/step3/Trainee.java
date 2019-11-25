package step3;

import java.io.Serializable;

@SuppressWarnings("serial")
class Trainee extends Human implements Serializable {
	private String stdNo;

	public Trainee(String name, int age, String jumin, String stdNo) {
		super(name, age, jumin);
		this.stdNo = stdNo;
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	@Override
	public void showInfo() {
		super.showInfo();
		System.out.printf(", 학번: %s%n", stdNo);
	}
}
