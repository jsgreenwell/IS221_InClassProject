import java.io.IOException;
import java.util.Scanner;

// Main will simply run the menu and "app loop"
// See individual classes for their descriptions
public class Main {
    public static void main(String[] args) {
        // Scanner option to take in choice (for 25+ can use IO)
        // final = immutable as it's always a Scanner object
        final Scanner scan = new Scanner(System.in);

        // New classes - we'll explore these as we learn about classes
        final Employee employeeFunctions = new Employee();
        final Clients clients = new Clients();

        // Let's create a menu - move this to a String array
        boolean run = true; // keep running until false
        while (run) {
            System.out.println("""
                    Please select from the following options:
                        1 Get and Set department email & phone number
                        2 Verify a gift card
                        3 Calculate compensation information
                        4 Set or Get Work Dates
                        5 Display Services
                        6 Client Bills
                        7 Client Reviews
                        8 Display Department Information
                        9 Exit this program
                    """);
            short choice = Short.parseShort(scan.nextLine());
            switch (choice) {
                case 0:
                    Position position = new Position();
                    System.out.println(position.allLines);
                    System.out.println(position);
                    System.out.println("-------------------");
                    break;
                case 1:
                    employeeFunctions.printEmployeeInfo();

                    System.out.print("Do you want to change the info (y/n): ");
                    if (scan.nextLine().toLowerCase().startsWith("y")) {
                        employeeFunctions.changeInfo();
                    }
                    break;
                case 2:
                    GiftCard gc = new GiftCard();
                    System.out.println(gc);
                    System.out.println("Hit enter to continue...");
                    scan.nextLine();
                    break;
                case 3:
                    // Get and calculate the employee pay and travel comp
                    System.out.print("How many hours did you work: ");
                    System.out.printf("You earned %.2f\n",
                            employeeFunctions.getSalary(Double.parseDouble(scan.nextLine())
                    ));

                    // Get the miles & gallons used and see how much we can compensate
                    System.out.print("How many miles did you drive: ");
                    double miles =  Double.parseDouble(scan.nextLine());
                    System.out.print("How many gallons of gas did you use: ");
                    double gallons =  Double.parseDouble(scan.nextLine());

                    System.out.println(employeeFunctions.calcTravelComp(miles, gallons));

                    System.out.println("Press Enter to continue...");
                    scan.nextLine();
                    break;
                case 4:
                    employeeFunctions.getWorkDates();

                    System.out.print("Do you want to add or change any dates? (y/n): ");
                    if (scan.nextLine().toLowerCase().startsWith("y")) {
                        employeeFunctions.changeWorkDates();
                    }
                    break;
                case 5:
                    clients.displayServices();
                    break;
                case 6:
                    clients.bills();
                    break;
                case 7:
                    clients.reviews();
                    break;
                case 8:
                    Departments depts = new Departments();
                    while(depts.running) {
                        depts.run();
                    }
                case 9:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice.");

            }
        }

        // Exit program with message
        System.out.println("Thanks for using the program!");
    }
}