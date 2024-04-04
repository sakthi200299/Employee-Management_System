import java.util.Scanner;
import java.util.Map;
import java.util.HashMap; 
public class Employee {                                               // class name Employee
    private static EmployeeService employeeService;                   //create object for EmployeeService

    public static void main(String[] args) {
        employeeService = new EmployeeServiceImpl();
        Scanner scanner = new Scanner(System.in);                    //Scanner class for  getting input 
        while (true) {
			System.out.println("Employee Management System");        //Employee Management System
            System.out.println("Choose an option:");                 //Choose an option for spenfic operation
            System.out.println("1) Add");                            //Select 1 for add the Employee details
            System.out.println("2) Modify");                         //Select 2 for Modify the Employee details
            System.out.println("3) Delete");                         //Select 3 for Delete the Employee details
            System.out.println("4) Display");                        //Select 4 for Display the Employee details
            System.out.println("5) Exit");                                 
            int choice = scanner.nextInt();
            scanner.nextLine();                                                     // Consume newline

            switch (choice) {
                case 1:
                    Map<String, Object> employeeinsert = new HashMap<>();        //create the Map for insert the employee details
                    System.out.println("Enter employee ID:");
                    employeeinsert.put("employeeId", scanner.nextInt());            //Enter  a employee ID
                    scanner.nextLine();                                                // Consume newline
                    System.out.println("Enter employee name:");
                    employeeinsert.put("employeeName", scanner.nextLine());         //Enter  a employee name
					System.out.println("Enter a employee EmailId");
					employeeinsert.put("employeeEmail",scanner.nextLine());         //Enter  a employee email
					System.out.println("Enter a employee experience");
					employeeinsert.put("employeeExperience",scanner.nextInt());      //Enter  a employee experience
                    System.out.println("Enter created by:");
                    employeeinsert.put("createdBy", scanner.next());                 //Enter  a employee createdBy
                    System.out.println("Is employee active? (true or false):");
                    employeeinsert.put("isActive", scanner.nextBoolean());            //Enter  a employee isActive
                    scanner.nextLine();                                                      // Consume newline
                    employeeService.add(employeeinsert);
                    break;
                case 2:
                   
                 Map<String, Object> employeemodify = new HashMap<>(); //create the Map for Modify the employee Details
                 System.out.println("Enter employee ID to modify:");
                 employeemodify.put("employeeId", scanner.nextInt()); //Enter a employee ID to modify
                 scanner.nextLine();
                 System.out.println("Enter a choice for modify"); // Choose an option for specific operation
                 System.out.println("Enter 1 to Modify Employee name"); //Enter a 1 employee name to modified
                 System.out.println("Enter 2 to Modify Employee emailID"); //Enter a 2 employee emailID to modified
                 System.out.println("Enter 3 to Modify Employee experience"); //Enter a 3 employee Experience to modified
                 System.out.println("Enter 4 to Exit"); // Enter a 4 for exit the while loop
                 int choicemodify = scanner.nextInt();
                 scanner.nextLine(); // Consume newline

                switch (choicemodify) {
                        case 1:
                          System.out.println("Enter name to Modify:");
                          employeemodify.put("employeeName", scanner.nextLine()); //enter the employeeName for modified
                        break;
                        case 2:
                          System.out.println("Enter an employee EmailId to Modify");
                          employeemodify.put("employeeEmail", scanner.nextLine()); //enter the employee email for modified
                         break;
                        case 3:
                          System.out.println("Enter an employee experience to Modify");
                          employeemodify.put("employeeExperience", scanner.nextInt()); //enter the employee experience for modified
                          scanner.nextLine(); // Consume newline
                         break;
                         case 4:
                         System.out.println("Exiting modification...");
                      return; // exit the case 2 section
                      default:
                      System.out.println("Invalid choice for modification.");
                      return; // exit the case 2 section
                         }

                           System.out.println("Enter modified by:");
                           employeemodify.put("modifiedBy", scanner.next()); //Enter a modified by
                           System.out.println("Is employee active? (true or false):");
                           employeemodify.put("isActive", scanner.nextBoolean()); //Enter isActive to modified
                           employeeService.modify(employeemodify);
                     break;
               
                case 3:
               
                   System.out.println("Enter Employee ID to Delete");
                   int deleteemployeeid=scanner.nextInt();
                   employeeService.delete(deleteemployeeid);                       //enter a employeeId to delete
                 
                   break;
                case 4:
				      System.out.println("Display there will be Choice");                      // Choose an option for spenfic operation
					  System.out.println("Enter a 1 for Display by Employee Id");              //Enter a 1 employee id to display
                      System.out.println("Enter a 2 for Display by Employee Name");            //Enter a 2 employee name to display
                      System.out.println("Enter a 3 for Display by ALL Employee Details ");      //Enter a 3 All employee  to  display
					  System.out.println("Enter a 4 for Exit");
					   int choicedisplay;
                     while(true)
					 {	
				       choicedisplay=scanner.nextInt();
 				       if(choicedisplay==1)
					   {
                         System.out.println("Enter the Employee Id to display:");
                          int  employeeIdToDisplay=scanner.nextInt();                    //enter a employeeID to display 
						  employeeService.displayEmployeeById(employeeIdToDisplay);       //display the employee by id method 
					   }
					   else if(choicedisplay==2)
					   {
					      System.out.println("Enter the Employee Name to display:");
					      String employeenamedisplay=scanner.next();                         //enter a employee name to display 
						  employeeService.displayEmployeesByName(employeenamedisplay);     //display the employee by name method 
					   }
					   else if(choicedisplay==3)
					   {
					          employeeService.displayEmployees();                          //display the all employee                 
					   }
					   else if(choicedisplay==4)
					   {
						   break;
					   }
					}
					  
					  
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();                               //close the scanner class
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
