package model;

import exception.BirthDateException;
import exception.NotMatchException;
import exception.TextLengthException;

import java.util.GregorianCalendar;

public class Passenger extends Person {
    private final static int PASSPORT_LENGTH = 9;

    private String passportNumber;          // 9 characters
    private GregorianCalendar birthdate;    // JJ/MM/AAAA ( < 1 week before today)

    //region Constructors
    public Passenger(String firstpassportNumber, String lastpassportNumber, String phoneNumber, String emailAddress, String passportNumber, GregorianCalendar birthdate)
            throws TextLengthException, BirthDateException, NotMatchException {
        super(firstpassportNumber, lastpassportNumber, phoneNumber, emailAddress);
        setPassportNumber(passportNumber);
        setBirthdate(birthdate);
    }
    //endregion

    //region Setters
    private void setPassportNumber(String passportNumber) throws TextLengthException {
        if (!(passportNumber.length() == PASSPORT_LENGTH)) {
            throw new TextLengthException("Le numéro du passeport doit contenir exactement " + PASSPORT_LENGTH + " caractères");
        } else {
            this.passportNumber= passportNumber;
        }
    }

    private void setBirthdate(GregorianCalendar birthdate) throws BirthDateException {
        GregorianCalendar currentDate = new GregorianCalendar();
        this.birthdate = birthdate;

        if (birthdate.compareTo(currentDate) < 0) {
            this.birthdate = birthdate;
        } else {
            throw new BirthDateException(birthdate);
        }
    }
    //endregion
}
