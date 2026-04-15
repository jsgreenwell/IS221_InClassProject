import java.util.*;

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

    // Yes you can set up a Class within a Class - this is composition
    // Cause its inside Clients.java - no encapsulation (it sets up same as Clients)
    private static class Reviews {
        long employeeID;
        long clientID;
        long receipt;
        String clientName;
        String reviewText;
        short reviewRating;

        Reviews(long receipt, long employeeID, long clientID, String clientName, String reviewText, short reviewRating) {
            this.receipt = receipt;
            this.employeeID = employeeID;
            this.clientID = clientID;
            this.clientName = clientName;
            this.reviewText = reviewText;
            this.reviewRating = reviewRating;
        }
    }

    /**
     * Completed in class with 2 sprints: Starting code is (1st Sprint completed by me)
     * - Create an array of reviews (10 integers between 0-100)
     * - Display these and determine the average
     * - Use average to determine if we need to talk to supervisor
     * ==================================================================
     * Second Sprint: use a Map to track reviews & build a BoW
     * - key is the sales receipt # (transaction #)
     * - values include: employeeID, clientID, clientName, ReviewText, ReviewRating, followup?
     * - We'll make then compare with a class (which is better)
     */
    public void reviews() {
        List<Reviews> reviews = createReviews();
        int counter = 0;
        int ratingSum = 0; // ratings between 1 & 10

        // This map will hold employee ratings (id & rating score)
        Map<String, Long> empReviews = new HashMap<>();
        empReviews.put("employeeID", 201L);
        empReviews.put("total", 0L);
        empReviews.put("count", 0L);

        System.out.println("Reviews (with transaction #, client name, & text of review):");
        for  (Reviews review : reviews) {
            System.out.println("\t------------------------------------------------------------------------");
            System.out.println("\t" + review.clientName + " " + review.reviewText);
            System.out.println("\t------------------------------------------------------------------------");

            if (review.employeeID == 201) {
                empReviews.put("count", empReviews.get("count") + 1);
                empReviews.put(String.format("rating%d", empReviews.get("count")),
                        (long) review.reviewRating);
                if (empReviews.containsKey("total")) {
                    empReviews.put("total", (long) review.reviewRating + empReviews.get("total"));
                } else {
                    empReviews.put("total", (long) review.reviewRating);
                }
            }

            // calculate out sum of reviews & count of reviews
            ratingSum += review.reviewRating;
            counter++;
        }

        System.out.printf("The average rating is %.2f", ratingSum * 1.0/counter);
        System.out.printf("The average rating is for %d is %.2f",
                empReviews.get("employeeID"),
                empReviews.get("total") * 1.0/empReviews.get("count"));

        // Let's review that employee's ratings:
        for (String key : empReviews.keySet()) {
            System.out.println(key + " : with value : " + empReviews.get(key));
        }
    }

    private List<Reviews> createReviews() {
        // This should be a read from a file but want to keep it single file
        return Arrays.asList(
                new Reviews(100001L, 201L, 301L, "Alice Carter", "Great service and very friendly.", (short) 5),
                new Reviews(100002L, 202L, 302L, "Bob Nguyen", "Quick checkout and helpful staff.", (short) 4),
                new Reviews(100003L, 203L, 303L, "Carlos Mendez", "Employee answered all my questions.", (short) 5),
                new Reviews(100004L, 201L, 304L, "Dana Wells", "Clean store and good selection.", (short) 4),
                new Reviews(100005L, 202L, 305L, "Evan Brooks", "Had to wait a bit longer than expected.", (short) 3),
                new Reviews(100006L, 203L, 306L, "Fatima Ali", "Very polite and professional service.", (short) 5),
                new Reviews(100007L, 201L, 307L, "George Kim", "Checkout was smooth and fast.", (short) 4),
                new Reviews(100008L, 202L, 308L, "Hannah Ortiz", "Employee seemed rushed but helpful.", (short) 3),
                new Reviews(100009L, 203L, 309L, "Ivan Petrov", "Excellent experience overall.", (short) 5),
                new Reviews(100010L, 201L, 310L, "Julia Stein", "Found everything I needed quickly.", (short) 4),
                new Reviews(100011L, 202L, 301L, "Alice Carter", "Service was decent this time.", (short) 4),
                new Reviews(100012L, 203L, 302L, "Bob Nguyen", "Very knowledgeable employee.", (short) 5),
                new Reviews(100013L, 201L, 303L, "Carlos Mendez", "Long wait, but issue was resolved.", (short) 3),
                new Reviews(100014L, 202L, 304L, "Dana Wells", "Friendly greeting upon arrival.", (short) 4),
                new Reviews(100015L, 203L, 305L, "Evan Brooks", "Amazing customer support.", (short) 5),
                new Reviews(100016L, 201L, 306L, "Fatima Ali", "Checkout process could be better.", (short) 3),
                new Reviews(100017L, 202L, 307L, "George Kim", "Employee went above and beyond.", (short) 5),
                new Reviews(100018L, 203L, 308L, "Hannah Ortiz", "Store was well organized.", (short) 4),
                new Reviews(100019L, 201L, 309L, "Ivan Petrov", "Not very satisfied with the service.", (short) 2),
                new Reviews(100020L, 202L, 310L, "Julia Stein", "Overall a positive experience.", (short) 4)
        );
    }
}
