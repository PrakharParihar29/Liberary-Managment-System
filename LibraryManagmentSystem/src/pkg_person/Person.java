package pkg_person;

import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable{

	protected String name;
	protected String emailID;
	protected String phoneNumber;
	protected String address;
	protected String dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean isValidName = Pattern.matches("[a-zA-Z]+\\s[a-zA-Z]+",name);
		if(isValidName)
			this.name = name;
		else
			this.name = "default name";
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		boolean isValidEmailID = Pattern.matches("^[a-zA-Z][a-zA-Z0-9._]*[a-zA-Z0-9]+@[a-z]+[\\.][a-z]{2,3}$",emailID);
		if(isValidEmailID)
			this.emailID = emailID;
		else
			this.emailID = "Default emailId";
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		boolean isValidPhoneNumber = Pattern.matches("[6-9][0-9]{9}", phoneNumber);
		if(isValidPhoneNumber)
			this.phoneNumber = phoneNumber;
		else
			this.phoneNumber = "0000000000";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isValidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if(isValidDob)
			this.dob = dob;
		else
			this.dob = "01-06-2005";
	}
	
	public Person(String name, String emailID, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.setEmailID(emailID);
		this.setPhoneNumber(phoneNumber);
		this.address = address;
		this.setDob(dob);
	}
	
	public Person() {
		super();
	}
	
	
}
