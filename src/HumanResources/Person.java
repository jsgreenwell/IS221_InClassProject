package HumanResources;

public class Person {
        // Class variables for Person
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNum;

        public Person(String firstName, String lastName, String email, String phoneNum) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNum = phoneNum;
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
}
