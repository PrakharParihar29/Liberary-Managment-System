package pkg_person;

public class Student extends Person {
	private int rollNo;
	private int std;
	private String division;
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public int getStd() {
		return std;
	}
	public void setStd(int std) {
		this.std = std;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", std=" + std + ", division=" + division + ", name=" + name + ", emailID="
				+ emailID + ", phoneNumber=" + phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	public Student(String name, String emailID, String phoneNumber, String address, String dob, int rollNo, int std,
			String division) {
		super(name, emailID, phoneNumber, address, dob);
		this.rollNo = rollNo;
		this.std = std;
		this.division = division;
	}
	public Student() {
		super();
	}
}
