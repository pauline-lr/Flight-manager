package model;

import java.util.GregorianCalendar;

public class Passenger extends Person {
    private String passportNumber;
    private GregorianCalendar birthdate;

    public Passenger(String firstName, String lastName, String phoneNumber, String emailAddress, String passportNumber, GregorianCalendar birthdate) {
        super(firstName, lastName, phoneNumber, emailAddress);
        this.passportNumber = passportNumber;
        this.birthdate = birthdate;
    }
}
