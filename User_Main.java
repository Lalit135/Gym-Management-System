

import java.util.Scanner;

public class User_Main {
	
public static void main(String[] args) {
		
		int input;
		
		Scanner sc = new Scanner(System.in);
		
		User_Function US = new User_Function();
		System.out.println("<-- USER PANEL -->");
		System.out.println();
		
		// User panel menu.
		US.menu();
		
		while(true) {
			
			input = sc.nextInt();
			if(input>3) {
				System.out.println("Enter correct choice");
				US.menu();
			}
			
			switch (input) {
			
			// Account creation.
			case 1:
				US.GeneratingID();
				break;
				
			// Login to your account.	
			case 2:
				US.Login();
				break;
				
			// Terminate the program.	
			case 3:
				US.Exit();
				break;

			default:
				break;
			}
		}
		
	}

}
