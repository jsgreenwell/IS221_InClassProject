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

        public Person(String firstName, String lastName, String email, String phoneNum) {
            // Using a built-in library we generate a Unique ID
            ID = UUID.randomUUID().toString().replace("-", "");
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNum = phoneNum;
        }

    public Person() {
        // Default constructor to allow for UUID generation
        ID = UUID.randomUUID().toString().replace("-", "");
    }

    public String getName() {
            return firstName +  " " + lastName;
        }

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

    protected void setAddress(String fullAddress) {
        // here the full Address is a string like "9999 4th Ave, Seattle, WA 98104"
        String[] addressParts = fullAddress.split(" ");
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

    public void setAddress(Map<String, String> mappedAddress) {
            setAddress(String.format("%s %s %s %s",
                    mappedAddress.get("address"), mappedAddress.get("city"),
                    mappedAddress.get("state"), mappedAddress.get("zipcode")
            ));

    }
}
