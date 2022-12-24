
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Methods {

	String Firstname, Lastname, Plan, Password = null;
	long Mobilenumber;
	int age, count = 0, UserId;
	boolean start = false;
	LocalDateTime DOA;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	ArrayList<Admin_Data> list = new ArrayList<>();
	Admin_Data adduser = null;

	public void readData() throws IOException {
		try {
			ois = new ObjectInputStream(new FileInputStream("User Details.txt"));
			list = (ArrayList<Admin_Data>) ois.readObject();
			start = true;
		} catch (Exception e) {
			start = false;

		} finally {
			ois.close();
		}

	}

	public void updateData() throws IOException {
		try {
			oos = new ObjectOutputStream(new FileOutputStream("User Details.txt"));
			oos.writeObject(list);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			oos.close();
		}
	}

	public void writeData(Admin_Data user) throws IOException {
		try {
			readData();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			oos = new ObjectOutputStream(new FileOutputStream("User Details.txt"));
			list.add(user);
			oos.writeObject(list);
			// System.out.println("Data Written In File");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			oos.close();
		}
	}

	public Admin_Data validateData() throws IOException {
		Admin_Data newuser = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("User Panel.txt"));
			newuser = (Admin_Data) ois.readObject();
			System.out.println("Data Written");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ois.close();
		}

		return newuser;
	}

	Scanner sc = new Scanner(System.in);

	// to check if mobile number is of 10 digit or not.
	public long checkmobilenumber(long mobile) {
		int count = 0;
		long tempmobile = mobile;
		while (mobile > 0) {
			mobile = mobile / 10;
			count++;
		}

		if (count == 10) {
			Mobilenumber = tempmobile;
		} else {
			System.out.println("Enter Correct Mobile Number: ");
			checkmobilenumber(sc.nextLong());
		}

		return Mobilenumber;
	}

	// TO auto generating ID.
	public int genereateUserId() {

		Random pin = new Random();
		int userid = pin.nextInt(1000);
		return userid;
	}// Auto ID Generation closed

	// Generating Member Account
	public void GeneratingID() {

		System.out.println("Enter the number of addmission ");
		int temp = sc.nextInt();

		for (int i = 0; i < temp; i++) {
			System.out.println("Enter your first name");
			Firstname = sc.next();
			System.out.println("Enter your last name");
			Lastname = sc.next();
			System.out.println("Enter your mobile number");
			Mobilenumber = checkmobilenumber(sc.nextLong());
			System.out.println("Enter your age");
			age = sc.nextInt();
			DOA = LocalDateTime.now();
			System.out.println("Joinig Date: " + DOA);
			System.out.println(" ");
			System.out.println("Choose Plan");
			System.out.println("1. 1-month");
			System.out.println("2. 3-month");
			System.out.println("3. 6-month");
			System.out.println("4. 12-month");
			int input = sc.nextInt();

			switch (input) {

				case 1:
					Plan = "1-Month";
					break;

				case 2:
					Plan = "3-Month";
					break;

				case 3:
					Plan = "6-Month";
					break;

				case 4:
					Plan = "12-Month";
					break;

				default:
					System.out.println("Choose Plan from the option");
					break;
			}
			UserId = genereateUserId();
			System.out.println("Your User ID: ");
			System.out.println(UserId);
			System.out.println("Create your Password");
			Password = sc.next();
			start = true;

			adduser = new Admin_Data(Firstname, Lastname, DOA, Plan, Password, UserId, Mobilenumber, age);
			// count++;
			try {
				writeData(adduser);
			} catch (Exception e) {
				
			}

		}
		System.out.println("Addmission done Sucessfully: ");

	}// Admission closed.

	// To show All the members account
	public void Showallaccount() {
		try {
			readData();
			start = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (start) {
			for (Admin_Data user : list) {
				if (user != null) {
					user.Display();
				}
			}
		} else {
			System.out.println("NO account to show creat aaccount first");
			GeneratingID();
		}
	}// Showallaccount closed.

	// Serach Method
	public void serach() {
		try {
			readData();
			start = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean found = false;
		if (start) {
			System.out.println("Enter the user ID to fetch account");
			UserId = sc.nextInt();
			System.out.println("Enter the Password");
			Password = sc.next();
			for (Admin_Data user : list) {
				if (user != null && (user.getUserID() == UserId && user.getPassword().equals(Password))) {
					user.Display();
					found = true;
				}
			}
		} else {
			System.out.println("No account to serch creat a account first");

		}
		if (!found) {
			System.out.println("Enter the Proper ID of the user");
		}
	}// Serach Method closed.

	// Delete Method
	public void Delete() {
		try {
			readData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Enter the ID you want to Delete");
		UserId = sc.nextInt();
		Admin_Data tempuser=null;
		for (Admin_Data user : list) {
			if (user != null && user.getUserID() == UserId) {
				tempuser =user;
				System.out.println("Account Deleted Sucessfully....");
			}
		}
		list.remove(tempuser);
		try {
			updateData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// Delte Method Closed

	// To update Plan
	public void UpdatePlan() {
		try {
			readData();
			// start = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean found = false;
		System.out.println(" Enter the ID you want to Update plan of ");
		UserId = sc.nextInt();
		System.out.println("Enter the Password");
		Password = sc.next();
		for (Admin_Data user : list) {
			if (user != null && (user.getUserID() == UserId && user.getPassword().equals(Password))) {
				System.out.println("Choose the new Plan");
				System.out.println("1. 1-month");
				System.out.println("2. 3-month");
				System.out.println("3. 6-month");
				System.out.println("4. 12-month");
				int input = sc.nextInt();

				switch (input) {

					case 1:
						Plan = "1-Month";
						break;

					case 2:
						Plan = "3-Month";
						break;

					case 3:
						Plan = "6-Month";
						break;

					case 4:
						Plan = "12-Month";
						break;

					default:
						System.out.println("Choose Plan from the option");
						break;
				}
				user.setPlan(Plan);
				found = true;
			}

		}
		if (found) {
			System.out.println("Plan Updated Sucessfully");
		}
		if (!found) {
			System.out.println("Something went wrong");
		}

		try {
			updateData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// Plan update closed.

	public void Updateaccount() {
		try {
			readData();
			// start = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean found = false;
		System.out.println(" Enter the ID you want to Update plan of ");
		UserId = sc.nextInt();
		System.out.println("Enter the Password");
		Password = sc.next();
		for (Admin_Data user : list) {
			if (user != null && (user.getUserID() == UserId && user.getPassword().equals(Password))) {
				System.out.println("Enter your first name");
				Firstname = sc.next();
				user.setFirstname(Firstname);
				System.out.println("Enter your last name");
				Lastname = sc.next();
				user.setLastname(Lastname);
				System.out.println("Enter your mobile number");
				Mobilenumber = checkmobilenumber(sc.nextLong());
				user.setMobilenumber(Mobilenumber);
				System.out.println("Enter your age");
				age = sc.nextInt();
				user.setAge(age);

				found = true;
			}

		}
		if (found) {
			System.out.println("Account updated sucessfully");
		}
		if (!found) {
			System.out.println("Something went wrong");
		}

		try {
			updateData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void uservalidation(){
		Admin_Data user = null;

		try {
			user = validateData();
		} catch (Exception e) {
			// TODO: handle exception
		}

		if(user != null){
			user.Display();

			System.out.println("Do You Want To Add This User or Not ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int key = sc.nextInt();

			switch(key){
				case 1: 
					try {
						writeData(user);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("User Validated: ");
					break;

				case 2:

					System.out.println("User Decline");
					break;
			}
		}
		else{
			System.out.println("No New User For Validation.");
		}
	}

}