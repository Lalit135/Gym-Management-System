

import java.util.Scanner;

public class Main {
	
    static void menu() {
        System.out.println("1. Add Member");
        System.out.println("2. Show All Account's");
        System.out.println("3. Search Account");
        System.out.println("4. Delete Account");
        System.out.println("5. Update Plan");
        System.out.println("6. Update Account Details");
		System.out.println("7. Validate User");
        System.out.println("8. Exit");
        System.out.println(' ');
    }

		public static void main(String[] args) {
			
			Scanner sc=new Scanner(System.in);
			
			Methods Call=new Methods();
			int input;
			
			
			
			while(true) {
				
				menu();
				System.out.println("Enter your choice");
				input=sc.nextInt();
				
				switch (input) {
				case 1:
					Call.GeneratingID();
					break;
					
				case 2:
					Call.Showallaccount();
					break;
					
				case 3:
					Call.serach();
					break;
					
				case 4:
					Call.Delete();
					break;
					
				case 5:
					Call.UpdatePlan();
					break;
					
				case 6:
					Call.Updateaccount();
					break;

				case 7:
					Call.uservalidation();
					break;
					
				case 8:
					System.out.println("Program Terminated!!!");
					System.exit(0);
					break;

				default:
					System.out.println("Enter the Correct choice");
					break;
				}
			}
		}
		
		
		
}