package model;

import exception.AiportException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Airport {
    private final static String REGEX_CODE = "^[A-Z][A-Z][A-Z]$";
    private final static int LENGTH_NAME = 50;
    private final static int LENGTH_COUNTRY = 50;

    private String code;        // 3 letters
    private String name;        // Max 50 characters
    private String country;     // Max 50 characters

    //region Constructors
    public Airport(String code, String name, String country)
            throws AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException {
        setCode(code);
        setName(name);
        setCountry(country);
    }
    //endregion

    //region Setters
    private void setCode(String code) throws AiportException.CodeException {
        Pattern r = Pattern.compile(REGEX_CODE);
        Matcher m = r.matcher(code);
        if (m.find())
            this.code = code;
        else
            throw new AiportException.CodeException(code);
    }

    private void setName(String name) throws AiportException.NameAirportException {
        if(name.length() <= LENGTH_NAME)
            this.name = name;
        else
            throw new AiportException.NameAirportException(name);
    }

    private void setCountry(String country) throws AiportException.CountryException {
        if(country.length() <= LENGTH_COUNTRY)
            this.country = country;
        else
            throw new AiportException.CountryException(country);
    }
    //endregion

    //region Getters

    public String getName() {
        return name;
    }

    //endregion
}
