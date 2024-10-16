package pkg_main;

import java.util.Scanner;

import pkg_Exception.BookNotFoundException;
import pkg_Exception.StudentNotFoundException;
import pkg_book.Book;
import pkg_book.BookManager;
import pkg_person.Student;
import pkg_person.StudentManager;
import pkg_transection.BookTransectionManager;

public class Main {

	public static void main(String[] args) {
		int choice;
		
		Scanner sc = new Scanner(System.in);
		
		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransectionManager btm= new BookTransectionManager();
		
		do {
			System.out.println("Enter 1 if Student\nEnter 2 if Librarian\nEnter 3 if want to Exit ");
			choice = sc.nextInt();
			
			if(choice ==1) { // if Student
				System.out.println("Enter Your Roll Number");
				int rollNo = sc.nextInt();
				try {
					Student s = sm.get(rollNo);
					if(s == null)
						throw new StudentNotFoundException();
					int stud_choice;
					
					do {
						System.out.println("Enter 1 to View All Books\nEnter 2 to Search Book by ISBN\nEnter 3 to List Books By Subjects\nEnter 4 to Issue a Book\nEnter 5 To Return A Book\nEnter 99 For Exit");
						stud_choice = sc.nextInt();
						switch(stud_choice) {
						case 1://view all Books
							System.out.println("All the Book Records ase: ");
							bm.viewAllBooks();
							break;
						case 2:///search by ISBN
							System.out.println("Please Enter ISBN of the book to search");
							int search_isbn = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if(book == null)
								System.out.println("No Book with this ISBN exists in our Liberary");
							else
								System.out.println(book);
							break;
						case 3://search by book
						System.out.println("Please Enter Subject to search: ");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4:// Issue a Book
							System.out.println("Enter ISBN to issue a Book");
							int issue_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try {
								if(book == null) 
									throw new BookNotFoundException();
								if(book.getAvailable_quntity() > 0) {
									if(btm.issueBook(rollNo, issue_isbn)) {
										book.setAvailable_quntity(book.getAvailable_quntity() - 1);
										System.out.println("Book Has Been Issued");
									}
								}
								else {
									System.out.println("Book Have Been Issued....");
								}
							}
							catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							
							break;
						case 5://Return a book
							System.out.println("Please Enter the ISBN to Return a Book: ");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book != null) {
								if(btm.returnBook(return_isbn, rollNo)) {
									book.setAvailable_quntity(book.getAvailable_quntity() + 1);
									System.out.println("Thank You for Returning the Book.");
								}
								else {
									System.out.println("Could not Return the Book.");
								}
							}
							else {
								System.out.println("Book with this ISBN Does not exists in our liberary");
							}
							break;
						case 99:
							System.out.println("Thank You for Using Library");
							break;
						default:
							System.out.println("Invalid Choice");
						}
					}while(stud_choice != 99);
				}
				catch(StudentNotFoundException snfe) {
					System.out.println(snfe);
				}
			}
			
			else if(choice ==2) {// if Librarian
				int lib_choice;
				do {
					System.out.println("Enter 11 to view all Students\nEnter 12 to Print a Student by Roll Number\nEnter 13 to Register a Student\nEnter 14 To Update the Student\nEnter 15 To Delete a Student");
					System.out.println("Enter 21 to view all Books\nEnter 22 to Print a Book by ISBN\nEnter 23 to Add a New Book\nEnter 24 to Update a Book\nEnter 25 to Delete a Book");
					System.out.println("Enter 31 to view all Transections\nEnter 99 to Exit");
					lib_choice = sc.nextInt();
					switch(lib_choice) {
					case 11:// view all Student
						System.out.println("All The Students Record");
						sm.viewAllStudents();
						break;
						
					case 12://search a Student based on Roll number
						System.out.println("Enter Roll Number to Fetch Student's Record");
						int get_rollNo = sc.nextInt();
						Student student = sm.get(get_rollNo);
						if(student == null)
							System.out.println("No Record Matches This Roll Number");
						else
							System.out.println(student);
						break;
					case 13:// Add a Student
						System.out.println("Enter Students Details to Add");
						String name, emailID, phoneNumber, address, dob, division;
						int rollNo, std;
						sc.nextLine();  // for call print step after choice(if we do not call then name = sc.nextLine(); Directly called After enter.)
						System.out.print("Name: ");
						name = sc.nextLine();
						
						System.out.print("E-mail ID: ");
						emailID = sc.nextLine();
						
						System.out.print("Phone Number: ");
						phoneNumber = sc.nextLine();
						
						System.out.print("Address: ");
						address = sc.nextLine();
						
						System.out.print("Date Of Birth: ");
						dob = sc.nextLine();
						
						System.out.print("Roll Number (Integer): ");
						rollNo = sc.nextInt();
						
						System.out.print("Standard (Integer): ");
						std = sc.nextInt();
						
						sc.nextLine();
						System.out.print("Division: ");
						division = sc.nextLine();
						
						student = new Student(name, emailID, phoneNumber, address, dob, rollNo, std, division);
						sm.addStudent(student);
						System.out.println("Student is Added");
						break;
						
					case 14://Update a Student
						System.out.println("Enter a Roll Number to Update a Record");
						int modify_rollNo = sc.nextInt();
						try {
							student = sm.get(modify_rollNo);
							if(student == null)
								throw new StudentNotFoundException();
							sc.nextLine();
							
							System.out.print("Name: ");
							name = sc.nextLine();
							
							System.out.print("E-mail ID: ");
							emailID = sc.nextLine();
							
							System.out.print("Phone Number: ");
							phoneNumber = sc.nextLine();
							
							System.out.print("Address: ");
							address = sc.nextLine();
							
							System.out.print("Date Of Birth: ");
							dob = sc.nextLine();
							
							System.out.print("Standard (Integer): ");
							std = sc.nextInt();
							
							sc.nextLine();
							System.out.print("Division: ");
							division = sc.nextLine();
							
							sm.updateStudent(modify_rollNo, name, emailID, phoneNumber, address, dob, std, division);
							System.out.println("Student Record is Updated");
						}
						catch(StudentNotFoundException snfe) {
							System.out.println(snfe);
						}
						break;
					case 15:// Delete a Student
						System.out.println("Enter Roll Number of Student you want to Delete");
						int delete_rollNo =   sc.nextInt();
						if(sm.deleteStudent(delete_rollNo))
							System.out.println("Student Record is Removed");
						else {
							System.out.println("No Record with given Roll Number exists");
						}
						break;
					case 21://view all Books
						bm.viewAllBooks();
						break;
					case 22://search a Book by ISBN
						System.out.println("Enter ISBN of the book to search");
						int search_isbn = sc.nextInt();
						Book book = bm.searchBookByIsbn(search_isbn);
						if(book == null)
							System.out.println("No Book with this ISBN exists in our Liberary");
						else
							System.out.println(book);
						break;
					case 23:// add book to Liberary
						System.out.println("Please Enter Book Details to Add");
						int isbn;
						String title;
						String author;
						String publisher;
						int edition;
						String subject;
						int available_quantity;
						
						System.out.println("ISBN: ");
						isbn = sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Title: ");
						title = sc.nextLine();
						
						System.out.println("Author: ");
						author = sc.nextLine();
						
						System.out.println("Publisher: ");
						publisher = sc.nextLine();
						
						System.out.println("Edition: ");
						edition = sc.nextInt();
						
						sc.nextLine(); //When we take String input after Integer input it is used
						
						System.out.println("Subject: ");
						subject = sc.nextLine();
						
						System.out.println("Available Quantity: ");
						available_quantity = sc.nextInt();
						
						book = new Book(isbn, title, author, publisher, edition, subject,available_quantity);
						bm.addBook(book);System.out.println("A book Record is added");
						break;
					case 24://update a Book
						System.out.println("Enter a ISBN of Book to update the rercord:\n");
						int update_isbn = sc.nextInt();
						try {
							book = bm.searchBookByIsbn(update_isbn);
							if(book == null)
								throw new BookNotFoundException();
							else {
								System.out.println("Enter Book Details to modify");
								sc.nextLine();
								
								System.out.println("Title: ");
								title = sc.nextLine();
								
								System.out.println("Author: ");
								author = sc.nextLine();
								
								System.out.println("Publisher: ");
								publisher = sc.nextLine();
								
								System.out.println("Edition: ");
								edition = sc.nextInt();
								
								sc.nextLine(); //When we take String input after Intiger input it is used
								
								System.out.println("Subject: ");
								subject = sc.nextLine();
								
								System.out.println("Available Quantity: ");
								available_quantity = sc.nextInt();
								
								bm.updateBook(update_isbn, title, author, publisher, edition, subject, available_quantity);
							}
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
					case 25://Delete a Book
						System.out.println("Enter a ISBN of Book to Delete the rercord:\n");
						int delete_isbn = sc.nextInt();
						try {
							book = bm.searchBookByIsbn(delete_isbn);
							if(book == null)
								throw new BookNotFoundException();
							else {
								bm.deleteBook(delete_isbn);
								System.out.println("Book Record is Deleted");
							}
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
					case 31://view all book Transaction
						System.out.println("All the transaction are: ");
						btm.showAll();
						break;
					case 99:
						System.out.println("Thank You for Using Library");
						break;
					default:
						System.out.println("Invalid Choice");
					}
				}while(lib_choice != 99);
			}
		}while(choice !=3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();
		sc.close();	
	}
}
