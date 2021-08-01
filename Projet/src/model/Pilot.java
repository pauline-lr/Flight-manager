package model;

import exception.NotMatchException;
import exception.TextLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pilot extends Person {
    private final static int LICENCE_LENGTH = 7;
    private final static String REGEX_HEURE_FORMAT = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";

    private String licenceNumber;           // 7 characters
    private String flyingTime;   // HH:MM positive

    //region Constructors
    public Pilot(String licenceNumber, String firstName, String lastName, String phoneNumber, String emailAddress, String flyingTime)
            throws NotMatchException, TextLengthException {
        super(firstName, lastName, phoneNumber, emailAddress);
        setLicenceNumber(licenceNumber);
        setFlyingTime(flyingTime);
    }
    //endregion

    //region Setters
    private void setLicenceNumber(String licenceNumber) throws TextLengthException  {
        if(licenceNumber.length() == LICENCE_LENGTH)
            this.licenceNumber = licenceNumber;
        else
            throw new TextLengthException("Le numéro de licence doit contenir exactement " + LICENCE_LENGTH + " caractères");
    }

    private void setFlyingTime(String flyingTime) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_HEURE_FORMAT);
        Matcher m = r.matcher(flyingTime);
        if (m.find())
            this.flyingTime = flyingTime;
        else
            throw new NotMatchException("Le format de l'heure", "HH:MM:SS");
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
