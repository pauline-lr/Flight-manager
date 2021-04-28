package model;

public class Person {
    private String firstName;       // Max 30 letters
    private String lastName;        // Max 30 letters
    private String phoneNumber;     // Max 20 digits
    private String emailAddress;    // Max 50 characters

    //region Constructors
    public Person(String firstName, String lastName, String phoneNumber, String emailAddress) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
    }
    //endregion

    //region Setters
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    //endregion

    //region Getters
    //endregion
}
