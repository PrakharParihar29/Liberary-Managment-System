package pkg_Exception;

public class StudentNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {
		super();
	}

	@Override
	public String toString() {
		return "StudentNotFoundException is Generated";
	}
}
