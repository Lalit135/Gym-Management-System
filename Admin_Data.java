

import java.io.Serializable;
import java.time.LocalDateTime;

public class Admin_Data implements Serializable {
	
	private String Firstname,Lastname,Plan,Password;
	private long Mobilenumber;
	private int age,UserID;
	private LocalDateTime DOA;

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public LocalDateTime getDOA() {
		return DOA;
	}

	public void setDOA(LocalDateTime dOA) {
		DOA = dOA;
	}

	public String getPlan() {
		return Plan;
	}

	public void setPlan(String plan) {
		Plan = plan;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public long getMobilenumber() {
		return Mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		Mobilenumber = mobilenumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Admin_Data(String firstname, String lastname, LocalDateTime dOA, String plan, String password, int userID,
			long mobilenumber, int age) {
		super();
		Firstname = firstname;
		Lastname = lastname;
		DOA = dOA;
		Plan = plan;
		Password = password;
		UserID = userID;
		Mobilenumber = mobilenumber;
		this.age = age;
	}

	
	// To display the entered details
	public void Display() {
		System.out.println(" ");
		System.out.println("First Name:  "+Firstname);
		System.out.println("Last Name: "+Lastname);
		System.out.println("Mobile Number: "+Mobilenumber);
		System.out.println("Age: "+age);
		System.out.println("Date of Addmission: "+DOA);
		System.out.println("Plan: "+Plan);
		System.out.println("User Id: "+UserID);
		System.out.println("Password : "+Password);
		System.out.println("**********************");
		
		
	}// Display method closed 

	public void user_Display() {
		System.out.println(" ");
		System.out.println("First Name:  "+Firstname);
		System.out.println("Last Name: "+Lastname);
		System.out.println("Mobile Number: "+Mobilenumber);
		System.out.println("Age: "+age);
		System.out.println("Date of Addmission: "+DOA);
		System.out.println("Plan: "+Plan);
		System.out.println("**********************");
		
		
	}// Display method closed 
	
}