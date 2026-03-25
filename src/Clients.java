import java.util.Scanner;

public class Clients {
    private final String[] SERVICES = {
            "Virus/Malware Removal", "Hardware Repair",
            "Digital Recovery", "Data Backup"
    };
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays services available for clients.
     */
    public void displayServices() {
        System.out.println("Services available include: ");
        for  (String service : SERVICES) {
            System.out.println("\t" + service + "\n");
        }
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    /**
     * This will allow one to look through bills.
     * Build an array of 5 (then 10) bills by client id (index)
     * Then ask if a client bought more (raise bill) or paid some (lower)
     * and adjust amounts in array.
     */
    public void bills() {
        double[] clientTotals = {2000, 3000, 10000.5, 11000, 2000};

        // Ask if making payment or not - and if they say pay (anything) then payment
        // Otherwise it will be a service purchase (credit)
        System.out.print("Did the client buy or make a payment?");
        boolean payment = scanner.nextLine().toLowerCase().startsWith("pay");

        System.out.print("Please enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Please enter the client id: ");
        int userIndex = Integer.parseInt(scanner.nextLine());

        if (userIndex >= 0 && userIndex < clientTotals.length) {
            if (payment) {
                clientTotals[userIndex] -= amount;
            } else {
                clientTotals[userIndex] += amount;
            }
        }

        System.out.printf("Client id %d new totals are %.2f",
                userIndex, clientTotals[userIndex]);
    }

    /**
     * This is the homework: complete it (and change this comment)
     * - Create an array of reviews (10 integers between 0-100)
     * - Display these and determine the average
     * - Use average to determine if need to talk to supervisor
     */
    public void reviews() {
    }
}
