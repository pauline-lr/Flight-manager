package model;

import exception.PersonException;
import exception.PilotException;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pilot extends Person {
    private final static int LICENCE_LENGTH = 7;
    private final static String REGEX_HEURE_FORMAT = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

    private String licenceNumber;           // 7 characters
    private GregorianCalendar flyingTime;   // HH:MM positive

    //region Constructors
    public Pilot(String firstName, String lastName, String phoneNumber, String emailAddress, String licenceNumber, GregorianCalendar flyingTime)
            throws PersonException.PhoneNumberException, PersonException.FirstNameException, PersonException.LastNameException,
            PersonException.EmailException, PilotException.LicenceNumberException, PilotException.FlyingFlightException {
        super(firstName, lastName, phoneNumber, emailAddress);
        setLicenceNumber(licenceNumber);
        setFlyingTime(flyingTime);
    }
    //endregion

    //region Setters
    private void setLicenceNumber(String licenceNumber) throws PilotException.LicenceNumberException {
        if(licenceNumber.length() == LICENCE_LENGTH)
            this.licenceNumber = licenceNumber;
        else
            throw new PilotException.LicenceNumberException(licenceNumber);
    }

    private void setFlyingTime(GregorianCalendar flyingTime) throws PilotException.FlyingFlightException {
        Pattern r = Pattern.compile(REGEX_HEURE_FORMAT);
        Matcher m = r.matcher((CharSequence) flyingTime);
        if (m.find())
            this.flyingTime = flyingTime;
        else
            throw new PilotException.FlyingFlightException(flyingTime);
    }
    //endregion

    //region Getters
    public String getName() {
        return getFirstName() + " " + getLastName();
    }
    public String getLicenceNumber() {
        return licenceNumber;
    }
    //endregion
}
