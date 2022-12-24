
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

public class User_Function extends Methods {

	private static final Calendar Calender = null;
	String Firstname, Lastname, Plan, Password;
	long Mobilenumber;
	int age, input1, i, user_id;
	boolean B = false;
	LocalDateTime DOA;

	// User_Data U = null;

	Scanner sc = new Scanner(System.in);

	// To check the no of digits, in mobile number. (10 digit)
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

	// Account creation method.
	@Override
	public void GeneratingID() {
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
			sendtoadmin(adduser);
		} catch (Exception e) {
			// TODO: handle exception
		}
		menu();

	}

	private void sendtoadmin(Admin_Data user) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("User Panel.txt"));
			oos.writeObject(user);
			oos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void Login() {
		Admin_Data testuser = null;
		try {
			readData();
			B = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (B == true) {
			int User;
			String Pass;
			System.out.println("Enter your User ID :");
			User = sc.nextInt();
			System.out.println("Enter your Password :");
			Pass = sc.next();
			for (Admin_Data user : list) {
				if (user.getUserID() == User && user.getPassword().equals(Pass)) {
					System.out.println("Login Successfull :)");
					System.out.println();
					testuser = user;

				}
			}
			Punch(testuser);

		} else {
			System.out.println("Create account first.");
			System.out.println();
			GeneratingID();
		}

	}

	public void menu() {
		System.out.println("1 -> Create Account");
		System.out.println("2 -> Login");
		System.out.println("3 -> Exit");
		System.out.println("Enter your choice :");
	}

	// Menu after login.
	public void Punch(Admin_Data user) {

		try {
			readData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("1 -> Punch In");
		System.out.println("2 -> Log out");
		System.out.println("3 -> See your details");
		System.out.println("4 -> Update your account");
		System.out.println("Enter your choice :");
		input1 = sc.nextInt();

		switch (input1) {

			case 1:
				Punch_In(user);
				break;

			case 2:
				Logout();
				break;

			case 3:
				if (user != null) {
					user.user_Display();
					Punch(user);
				}
				break;

			case 4:
				Update(user);
				break;
		}
		try {
			updateData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// To terminate the program.
	public void Exit() {
		System.out.println("Thank you for visiting :)");
		System.exit(0);
	}

	// Logout from the account.
	public void Logout() {
		System.out.println("Log out successfuly :)");
		System.out.println();
		menu();
	}

	// Punch In method, to record the date and time of Punch In.
	public void Punch_In(Admin_Data user) {
		try {
			readData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Punched Inn");
		LocalDate Date = LocalDate.now();
		System.out.println("Date : " + Date);
		Calendar cal = Calender.getInstance();
		System.out.println("Time : " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
		System.out.println();
		Punch_Out();

		
		
	}

	// Punch Out method, to record the date and time of Punch Out.
	public void Punch_Out() {

		try {
			readData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("1 -> Punch Out");
		System.out.println("2 -> Back");
		System.out.println("Enter your choice :");
		input1 = sc.nextInt();

		switch (input1) {

			case 1:
				System.out.println("Punched Out");
				LocalDate Date = LocalDate.now();
				System.out.println("Date : " + Date);
				Calendar call = Calender.getInstance();
				System.out.println("Time : " + call.get(Calendar.HOUR) + ":" + call.get(Calendar.MINUTE));
				System.out.println();
				try {
					updateData();
				} catch (Exception e) {
					// TODO: handle exception
				}
				Punch(adduser);	
				break;

			case 2:
				Punch(adduser);
				break;
		}
		
	}

	// To update your account details.
	public void Update(Admin_Data user) {
		boolean found = false;
		System.out.println(" Enter the ID you want to Update plan of ");
		UserId = sc.nextInt();
		System.out.println("Enter the Password");
		Password = sc.next();
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

		if (found) {
			System.out.println("Account updated sucessfully");
		}
		if (!found) {
			System.out.println("Something went wrong");
		}

		try {
			updateData();
		} catch (Exception e) {
			// TODO: handle excep
		}
		menu();
	}

}
