package pkg_person;

public class Librarian extends Person {
	private int id;
	private int doj;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoj() {
		return doj;
	}
	public void setDoj(int doj) {
		this.doj = doj;
	}
	@Override
	public String toString() {
		return "Librarian [id=" + id + ", doj=" + doj + ", name=" + name + ", emailID=" + emailID + ", phoneNumbers="
				+ phoneNumber + ", address=" + address + ", dob=" + dob + "]";
	}
	public Librarian(String name, String emailID, String phoneNumbers, String address, String dob, int id, int doj) {
		super(name, emailID, phoneNumbers, address, dob);
		this.id = id;
		this.doj = doj;
	}
	public Librarian() {
		super();
	}
}
