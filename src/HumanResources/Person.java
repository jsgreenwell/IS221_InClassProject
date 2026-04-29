package HumanResources;

import java.util.Map;
import java.util.UUID;

public class Person {
        // Class variables for Person
        private final String ID;
        protected String firstName;
        protected String lastName;
        protected String middleName = "nmn";
        protected String preferredName;
        protected String email;
        protected String phoneNum;
        private String address;
        private String city;
        private String state;
        private String zipcode;

    /**
     * Create a Person instance with generated ID for session.
     * @param firstName the first name
     * @param lastName the last name
     * @param email email in format "blah@foo.com"
     * @param phoneNum phone with or without "-"
     */
    public Person(String firstName, String lastName, String email, String phoneNum) {
        // Using a built-in library we generate a Unique ID
        ID = UUID.randomUUID().toString().replace("-", "");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    /**
     * Just generates the randomUUID - no other values set
     * May cause exception if not set before use
     */
    public Person() {
        // Default constructor to allow for UUID generation
        ID = UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Returns first and last name combined
     * @return The first and last name of person
     */
    public String getName() {
        return firstName +  " " + lastName;
    }

    /**
     * Given an array of first and last name - this sets the values
     * TODO: add handling for first middle last & wrapper for other versions
     * @param name An array of the first and last name
     */
    public void setName(String[] name) {
        if (name.length == 2) {
            this.firstName = name[0];
            this.lastName = name[1];
        } else {
            System.out.println("Error: Name must be in the format 'First Last'");
        }
    }

    protected String getID() {
        return ID;
    }

    public boolean verifyID() {
        return ID.equals(getID().trim());
    }

    public String getAddress() {
        return String.format("%s, %s, %s %s", address, city, state, zipcode);
    }

    /**
     * Sets the address given a string of the full Address.
     * @param fullAddress A string with comma separating the address, city, state, and zipcode (in that order).
     */
    protected void setAddress(String fullAddress) {
        // here the full Address is a string like "9999 4th Ave, Seattle, WA, 98104"
        String[] addressParts = fullAddress.split(", ");
        this.address = addressParts[0].trim().toLowerCase();
        this.city = addressParts[1].trim().toLowerCase();
        this.state = addressParts[2].trim().toUpperCase().substring(0, 2);
        if (addressParts[3].trim().length() == 5) {
            try {
                int numericZip = Integer.parseInt(addressParts[3].trim()); // Over a for loop
                this.zipcode = numericZip + ""; // silly easy way to convert back to string
            } catch (NumberFormatException e) {
                zipcode = "99999";
            }
        } else {
            zipcode = "99999";
        }
    }

    /**
     * Given a map of the address - sets each associated value in the class
     * @param mappedAddress Map of address with keys: address, state, city, zipcode
     */
    public void setAddress(Map<String, String> mappedAddress) {
            setAddress(String.format("%s, %s, %s, %s",
                    mappedAddress.get("address"), mappedAddress.get("city"),
                    mappedAddress.get("state"), mappedAddress.get("zipcode")
            ));

    }
}
