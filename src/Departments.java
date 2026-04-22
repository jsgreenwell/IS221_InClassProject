import java.util.Map;
import java.util.Scanner;

public class Departments {
    // We'll use this for examples in class
    // scan variable to share among class methods
    private final Scanner scan = new Scanner(System.in);
    public boolean running = true;

    private final Map<Integer, String> options = Map.of(
            1, "Display All Department Contact Information",
            2, "Search for and display a department's contact information",
            3, "Display Department Services"
    );

    public void run() {
        // Clear the screen and display options
        clearScreen();

        int choice = selectOption();

        if (options.containsKey(choice)) {
            System.out.println("This is what we will make in class.");

        }

        // No "else" needed - it has to exit if number doesn't exist
        // We are done - so exit Departments
        System.out.print("Thank you for using Departments. Press enter to exit...");
        scan.nextLine();
        clearScreen();

        running = false;
    }

    private int selectOption() {
        System.out.println("Please select from the following options:");
        for (int key : options.keySet()) {
            System.out.println("\t" + key + ": " + options.get(key));
        }

        int choice = -1; // default value to trigger error if not changed
        try {
            choice = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            // loop through or retry
        }
        return choice;

    }

    private void clearScreen() {
        // This just prints a new line 40 times to clear the screen
        System.out.println("\n".repeat(40));
    }

}
