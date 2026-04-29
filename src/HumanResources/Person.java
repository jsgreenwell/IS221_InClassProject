package HumanResources;

import java.util.Map;
import java.util.UUID;

public class Person {
        // Class variables for Person
        private String ID;
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
        // Default constructor to allow for UUID generation and return Object
        // This is now a wrapper or helper function
        // As it just alters the passed values and passes them on to another method
        new Person("", "", "", "");
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

    /** I decided on having a protected function
     * As this allows HR components to use ID but not external packages
     * They can only verify it.
     * @return Session UUID as string
     */
    protected String getID() {
        return ID;
    }

    /**
     * Given an ID - verifies if it matches the current session ID
     * @param checkID A UUID (without "-") to check
     * @return True or False based on matching
     */
    public boolean verifyID(String checkID) {
        return ID.equals(checkID.trim());
    }

    /**
     * Builds a string of the address parts in the format "address, city, state zipcode"
     * @return Address in standard format
     */
    public String getAddress() {
        return String.format("%s, %s, %s %s", address, city, state, zipcode);
    }

    /**
     * Given an array of the location (city, state or zipcode) - verifies if it matches the current session location
     * @param location A string array of the city & state or the zipcode alone
     * @return true if values match person's location
     */
    public boolean verifyLocation(String[] location) {
        if  (location.length == 0) {
            // Nothing sent - no need to check
            return false;
        } else if (location.length == 2) {
            // must have sent city and state
            return location[0].trim().equalsIgnoreCase(city) && location[1].trim().equalsIgnoreCase(state);
        } else {
            // They sent 1 or more than 2 so just check if zip matches first field
            // no reason for Ignore Case because its numbers
            return location[0].trim().equals(zipcode);
        }
    }

    /**
     * Given an integer zipcode - this will verify if it matches
     * Polymorphism (an overloaded method) as it has the same name but different signature
     * @param zipcode The integer value of the zipcode
     * @return true if zipcode matches person's zipcode
     */
    public boolean verifyLocation(int zipcode) {
        // Just check if zipcode matches - could wrap this with above but check is simple
        return String.valueOf(zipcode).trim().equals(this.zipcode);
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
