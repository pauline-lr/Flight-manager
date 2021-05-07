package model;

import exception.PassengerException;

import java.util.GregorianCalendar;

public class Passenger extends Person {
    private String passportNumber;          // 9 characters
    private GregorianCalendar birthdate;    // JJ/MM/AAAA ( < 1 week before today)

    //region Constructors
    public Passenger(String firstpassportNumber, String lastpassportNumber, String phoneNumber, String emailAddress, String passportNumber, GregorianCalendar birthdate)
            throws PassengerException.PassportNumberException {
        super(firstpassportNumber, lastpassportNumber, phoneNumber, emailAddress);
        setPassportNumber(passportNumber);
        setBirthdate(birthdate);
    }
    //endregion

    //region Setters
    private void setPassportNumber(String passportNumber) throws PassengerException.PassportNumberException {
        if(passportNumber.length() <= 9)
            this.passportNumber = passportNumber;
        else
            throw new PassengerException.PassportNumberException(passportNumber);
    }
    private void setBirthdate(GregorianCalendar birthdate) {
        this.birthdate = birthdate;
    }
    //endregion

    //region Getters
    //endregion
}
