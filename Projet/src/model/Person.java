package model;

import exception.PersonException;

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
            throws PersonException.FirstNameException, PersonException.LastNameException, PersonException.PhoneNumberException, PersonException.EmailException {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
    }
    //endregion

    //region Setters
    private void setFirstName(String firstName) throws PersonException.FirstNameException {
        if(firstName.length() <= LENGTH_FIRST_NAME)
            this.firstName = firstName;
        else
            throw new PersonException.FirstNameException(firstName);
    }

    private void setLastName(String lastName) throws PersonException.LastNameException {
        if(lastName.length() <= LENGTH_LAST_NAME)
            this.lastName = lastName;
        else
            throw new PersonException.LastNameException(lastName);
    }

    private void setPhoneNumber(String phoneNumber) throws PersonException.PhoneNumberException {
        Pattern r = Pattern.compile(REGEX_PHONE_NUMBER);
        Matcher m = r.matcher(phoneNumber);
        if (m.find())
            this.phoneNumber = phoneNumber;
        else
            throw new PersonException.PhoneNumberException(phoneNumber);
    }

    private void setEmailAddress(String emailAddress) throws PersonException.EmailException {
        Pattern r = Pattern.compile(REGEX_EMAIL);
        Matcher m = r.matcher(emailAddress);
        if (m.find())
            this.emailAddress = emailAddress;
        else
            throw new PersonException.EmailException(emailAddress);
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
