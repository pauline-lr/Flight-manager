package model;

import java.util.GregorianCalendar;

public class Pilot extends Person {
    private String licenceNumber;           // 7 characters
    private GregorianCalendar flyingTime;   // HH:MM positive

    //region Constructors
    public Pilot(String firstName, String lastName, String phoneNumber, String emailAddress, String licenceNumber, GregorianCalendar flyingTime) {
        super(firstName, lastName, phoneNumber, emailAddress);
        setLicenceNumber(licenceNumber);
        setFlyingTime(flyingTime);
    }
    //endregion

    //region Setters
    private void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }
    private void setFlyingTime(GregorianCalendar flyingTime) {
        this.flyingTime = flyingTime;
    }
    //endregion

    //region Getters
    //endregion
}
