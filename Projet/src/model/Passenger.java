package model;

import exception.FlightException;
import exception.PassengerException;
import exception.PersonException;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Passenger extends Person {
    private final static int PASSPORT_LENGTH = 9;

    private String passportNumber;          // 9 characters
    private GregorianCalendar birthdate;    // JJ/MM/AAAA ( < 1 week before today)
    private Date currentDate;

    //region Constructors
    public Passenger(String firstpassportNumber, String lastpassportNumber, String phoneNumber, String emailAddress, String passportNumber, GregorianCalendar birthdate)
            throws PassengerException.PassportNumberException, PersonException.PhoneNumberException,
                    PersonException.FirstNameException, PersonException.LastNameException, PersonException.EmailException {
        super(firstpassportNumber, lastpassportNumber, phoneNumber, emailAddress);
        setPassportNumber(passportNumber);
        setBirthdate(birthdate);
        currentDate = new Date(System.currentTimeMillis());
    }
    //endregion

    //region Setters
    private void setPassportNumber(String passportNumber) throws PassengerException.PassportNumberException {
        if(passportNumber.length() == PASSPORT_LENGTH)
            this.passportNumber = passportNumber;
        else
            throw new PassengerException.PassportNumberException(passportNumber);
    }

    private void setBirthdate(GregorianCalendar birthdate) {
        if(birthdate.before(currentDate)) {
            this.birthdate = birthdate;
        }else{
            try {
                throw new PassengerException.BirthDateException(birthdate);
            } catch (PassengerException.BirthDateException e) {
                e.printStackTrace();
            }catch (DateTimeParseException e ) {
                e.printStackTrace();
            }
        }
    }
    //endregion

    //region Getters
    //endregion
}
