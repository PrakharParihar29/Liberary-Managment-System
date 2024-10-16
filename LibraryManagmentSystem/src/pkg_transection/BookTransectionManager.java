package pkg_transection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookTransectionManager {
	ObjectOutputStream oos_book_transection = null;
	ObjectInputStream ois_book_transection = null;
	
	File book_transection_file = null;
	
	ArrayList<BookTransection> book_transection_list = null;
	
	@SuppressWarnings("unchecked")
	public BookTransectionManager() {
		book_transection_file = new File("book_transection.dat");
		book_transection_list = new ArrayList<BookTransection>();
		
		if(book_transection_file.exists()) {
			try {
				ois_book_transection = new ObjectInputStream(new FileInputStream(book_transection_file));
				book_transection_list = (ArrayList<BookTransection>) ois_book_transection.readObject();
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
		}
	}
	
	public boolean issueBook(int rollNo, int isbn) {
		int total_books_issued = 0;
		for(BookTransection book_transection:book_transection_list) {
			if((book_transection.getRollNo() == rollNo) && (book_transection.getReturnDate() == null))
				total_books_issued++;
			if(total_books_issued >= 3)
				return false;
		}
		String issue_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		BookTransection book_transection = new BookTransection(isbn,rollNo,issue_date,null);
		book_transection_list.add(book_transection);
		return true;
	}
	
	public boolean returnBook(int isbn,int rollNo) {
		for(BookTransection book_transection:book_transection_list) {
			if((book_transection.getRollNo() == rollNo) && (book_transection.getReturnDate() == null) && (book_transection.getIsbn() == isbn)) {
				String return_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
				book_transection.setReturnDate(return_date);
				return true;
			}
		}
		return false;
	}
	
	public void writeToFile() {
		try {
			oos_book_transection = new ObjectOutputStream(new FileOutputStream(book_transection_file));
			oos_book_transection.writeObject(book_transection_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAll() {
		for(BookTransection book_transection:book_transection_list)
			System.out.println(book_transection);
	}
}
