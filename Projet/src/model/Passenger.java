package model;

import java.util.GregorianCalendar;

public class Passenger extends Person {
    private String passportNumber;          // 9 characters
    private GregorianCalendar birthdate;    // JJ/MM/AAAA ( < 1 week before today)

    //region Constructors
    public Passenger(String firstName, String lastName, String phoneNumber, String emailAddress, String passportNumber, GregorianCalendar birthdate) {
        super(firstName, lastName, phoneNumber, emailAddress);
        setPassportNumber(passportNumber);
        setBirthdate(birthdate);
    }
    //endregion

    //region Setters
    private void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    private void setBirthdate(GregorianCalendar birthdate) {
        this.birthdate = birthdate;
    }
    //endregion

    //region Getters
    //endregion
}
