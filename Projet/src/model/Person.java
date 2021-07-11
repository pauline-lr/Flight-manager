package model;

import exception.NotMatchException;
import exception.TextLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private final static int LENGTH_FIRST_NAME = 30;
    private final static int LENGTH_LAST_NAME = 30;
    private final static String REGEX_PHONE_NUMBER = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    private final static String REGEX_EMAIL = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

    private String firstName;       // Max 30 letters
    private String lastName;        // Max 30 letters
    private String phoneNumber;     // Max 20 digits + respecter la structure d'un num de tel
    private String emailAddress;    // Max 50 characters + respecter la structure d'une adresse mail

    //region Constructors
    public Person(String firstName, String lastName, String phoneNumber, String emailAddress)
            throws TextLengthException, NotMatchException {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
    }
    //endregion

    //region Setters
    private void setFirstName(String firstName) throws TextLengthException {
        if(!(firstName.isEmpty()) && firstName.length() <= LENGTH_FIRST_NAME)
            this.firstName = firstName;
        else
            throw new TextLengthException("Le prénom");
    }

    private void setLastName(String lastName) throws TextLengthException {
        if(!(firstName.isEmpty()) && lastName.length() <= LENGTH_LAST_NAME)
            this.lastName = lastName;
        else
            throw new TextLengthException("Le nom de famille");
    }

    private void setPhoneNumber(String phoneNumber) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_PHONE_NUMBER);
        Matcher m = r.matcher(phoneNumber);
        if (m.find())
            this.phoneNumber = phoneNumber;
        else
            throw new NotMatchException("Le numéro de téléphone");
    }

    private void setEmailAddress(String emailAddress) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_EMAIL);
        Matcher m = r.matcher(emailAddress);
        if (m.find())
            this.emailAddress = emailAddress;
        else
            throw new NotMatchException("L'adresse mail");
    }
    //endregion

    //region Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    //endregion
}
