package model;

import java.util.GregorianCalendar;

public class Pilot extends Person {
    private String licenceNumber;
    private GregorianCalendar flyingTime;

    public Pilot(String firstName, String lastName, String phoneNumber, String emailAddress, String licenceNumber, GregorianCalendar flyingTime) {
        super(firstName, lastName, phoneNumber, emailAddress);
        this.licenceNumber = licenceNumber;
        this.flyingTime = flyingTime;
    }
}
