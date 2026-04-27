package HumanResources;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.round;

/**
 * HumanResources.Employee Class holds data and functions related to employee information.
 * This includes demographics, schedules, and compensation confirmation.
 */
public class Employee {
    // Internal variable for getting input
    private final Scanner input = new Scanner(System.in);
    // Set (final or constant) group of fields
    private final String[] FIELDS = {"name", "email", "phone number", "role", "role type"};
    // Variables to hold employee basic info
    protected String name = "Bob";
    protected String email = "boreily@mu.org";
    protected String phoneNumber = "2175559389";
    protected String role = "Manager";
    protected boolean fullTime = true;

    // Dates they are scheduled to work
    protected List<LocalDate> workDates = new ArrayList<>();

    /**
     * Prints out EmployeeInfo in a "pretty" way (line by line).
     */
    public void printEmployeeInfo() {
        System.out.printf("""
            Name: %s
            Email: %s
            Phone: %s
            Role: %s %s
            """, name, email, phoneNumber, role,
                // ternary operator = if/else shortcut
                fullTime ? "Full Time" : "Part Time");

        System.out.println("Press enter to continue...");
        input.nextLine();
    }

    /**
     * Allows users to change the HumanResources.Employee info.
     * Loops to allow them to change one by one as needed.
     */
    public void changeInfo() {
        while (true) {
            System.out.println("What field to you want to change?");
            System.out.print("\tOptions: ");
            for (String field : FIELDS) {
                System.out.print("\n\t\t" + field);
            }
            System.out.print("\nEnter field: ");

            switch (input.nextLine().toLowerCase()) {
                case "name":
                    System.out.print("Enter Name: ");
                    name = input.nextLine();
                    break;
                case "email":
                    System.out.print("Enter Email: ");
                    email = input.nextLine();
                    break;
                case "phone": case "phone number":
                    System.out.print("Enter Phone Number: ");
                    phoneNumber = input.nextLine();
                    break;
                case "role":
                    System.out.print("Enter Role: ");
                    role = input.nextLine();

                    // If role is Manager or Technician then set to true (don't need if)
                    fullTime = role.equalsIgnoreCase("Manager")
                               || role.equalsIgnoreCase("Technician");
                    break;
                default:
                    System.out.println("Invalid input!");
            }

            System.out.print("Do you want to change anything else? (y/n): ");
            if (!input.nextLine().toLowerCase().startsWith("y")) {
                System.out.println("Thanks for updating - information is now: ");
                printEmployeeInfo();
                return;
            }
        }
    }

    /**
     * This gets the salary of the employee based on their job role.
     * For part-time employees it calculates the information based on payrate
     * and time & a half for overtime (over 40 hours).
     * @param hours How many hours worked this week
     * @return Total compensation for worked hours or weekly pay
     */
    public double getSalary(double hours) {
        if (role.equalsIgnoreCase("Manager")) {
            return 879.5;
        } else if (role.equalsIgnoreCase("Technician")) {
            return 902.4;
        } else if (role.toLowerCase().contains("agent")) {
            // calculates out the regular pay + overtime (1.5 *)
            if (hours > 40) {
                return 15.48 * 40 + (hours % 40 * 1.5 * 15.48);
            } else {
                return 15.48 * hours;
            }
        } else if (role.toLowerCase().contains("service")) {
            // calculates out the regular pay + overtime (1.5 *)
            if (hours > 40) {
                return 12.45 * 40 + (hours % 40 * 1.5 * 12.45);
            } else {
                return 12.45 * hours;
            }
        } else if (role.toLowerCase().contains("rep")) {
            // calculates out the regular pay + overtime (1.5 *)
            if (hours > 40) {
                return 10.25 * 40 + (hours % 40 * 1.5 * 10.25);
            } else {
                return 10.25 * hours;
            }
        } else {
            return 0.0;
        }
    }

    /**
     * Determines, calculates, and displays how much a person will be compensated for travel.
     * They get 21 cents per mile and 1 dollar per gallon up to 12 gallons & 100 miles.
     * @param miles How many miles traveled
     * @param gallons How many gallons used
     * @return A string of the compensation: how much should be compensated per item
     */
    public String calcTravelComp(double miles, double gallons) {
        // Exit if miles or gallons are invalid (minimum 1 to 100 miles or 30 gallons)
        if (miles <= 0 || gallons <= 0) {
            return "Invalid miles or gallons - speak to supervisor.";
        }

        String toReturn = "Your mpg was " + round(miles/gallons) + ".\n";

        if (miles > 100 || gallons > 12) {
            toReturn += "You'll get $33 in compensation.\n";
            return toReturn;
        }

        toReturn += "You will be compensated for the full amount and $" + (miles*21/100.0)+gallons + ".\n";
        return toReturn;
    }

    public void getWorkDates() {
        if (workDates.isEmpty()) {
            System.out.println("No work dates on file for this or next month.");
        } else {
            System.out.println("Work Dates available with index: ");
            for (int i = 0; i < workDates.size(); i++) {
                System.out.printf("\t%d: %s",
                        i, workDates.get(i).toString());
            }
        }

        System.out.println("\nPress enter to continue...");
        input.nextLine();
    }

    private LocalDate validateDate() {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();

        System.out.print("Enter the date as YYYY-MM-DD: ");
        String[] userDates = input.nextLine().split("-");

        // Not enough values in userDates (or too many)
        if (userDates.length != 3) {
            System.out.println("Invalid date format!");
            return null;
        }

        if (currentMonth.getValue() == Integer.parseInt(userDates[1]) &&
             (Integer.parseInt(userDates[0]) == currentYear ||
             (currentMonth.name().equalsIgnoreCase("December")
                     && Integer.parseInt(userDates[0]) == currentYear+1))) {
            // Valid month & year - should check day
            // We'll just make sure its between 1 & 31:
            if (Integer.parseInt(userDates[2]) > 31 ||
                    Integer.parseInt(userDates[2]) < 0) {
                System.out.println("Invalid date format!");
                return null;
            }

            return LocalDate.of(Integer.parseInt(userDates[0]),
                    Integer.parseInt(userDates[1]),
                    Integer.parseInt(userDates[2]));

        }
        // Should never get here
        return null;
    }

    public void changeWorkDates() {

        while (true) {
            // Check what they want to do
            System.out.print("Do you want to add(a), delete(d), or change(c) dates?: ");
            String userAnswer = input.nextLine().toLowerCase();

            if (userAnswer.startsWith("a")) {
                LocalDate userDate = validateDate();
                if (userDate != null) {
                    workDates.add(userDate);
                    getWorkDates();
                }
            } else if (userAnswer.startsWith("c")) {
                if (workDates.isEmpty()) {
                    System.out.println("No work dates on file for this or next month.");
                } else {
                    getWorkDates();
                    System.out.print("Please enter an index to change: ");
                    int userIndex = input.nextInt();

                    if (userIndex > workDates.size() || userIndex < 0) {
                        System.out.println("Invalid index!");
                        return;
                    }

                    LocalDate userDate = validateDate();

                    if (userDate != null) {
                        workDates.set(userIndex, userDate);
                    }
                }
            } else if (userAnswer.startsWith("d")) {
                if (workDates.isEmpty()) {
                    System.out.println("No work dates on file for this or next month.");
                } else {
                    getWorkDates();
                    System.out.print("Please enter an index to change: ");
                    int userIndex = input.nextInt();

                    if (userIndex > workDates.size() || userIndex < 0) {
                        System.out.println("Invalid index!");
                        return;
                    }
                    workDates.remove(userIndex);
                }

                System.out.println("Do you want to add or change any other dates?");
                if (!input.nextLine().toLowerCase().startsWith("y")) {
                    System.out.println("Thanks for adding - information is now: ");
                    getWorkDates();
                    return;
                }
            }
        }
    }

}
