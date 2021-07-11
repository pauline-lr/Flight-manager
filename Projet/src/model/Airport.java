package model;

import exception.NotMatchException;
import exception.TextLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Airport {
    private final static String REGEX_CODE = "^[A-Z][A-Z][A-Z]$";
    private final static int LENGTH_NAME = 50;
    private final static int LENGTH_COUNTRY = 50;
    private final static int LENGTH_MIN = 2;

    private String code;        // 3 letters
    private String name;        // Max 50 characters
    private String country;     // Max 50 characters

    //region Constructors
    public Airport(String code, String name, String country)
            throws TextLengthException, NotMatchException {
        setCode(code);
        setName(name);
        setCountry(country);
    }
    //endregion

    //region Setters
    private void setCode(String code) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_CODE);
        Matcher m = r.matcher(code);
        if (!m.find())
            throw new NotMatchException("Le code de l'aéroport", "3 lettres majuscules");
        else
            this.code = code;
    }

    private void setName(String name) throws  TextLengthException {
        if(name.length() < LENGTH_MIN)
            throw new TextLengthException("Le nom de l'aéroport est trop court.\n Minimum " + LENGTH_MIN + " caractères.");
        else if(name.length() > LENGTH_NAME)
            throw new TextLengthException("Le nom de l'aéroport est trop long.\n Maximum " + LENGTH_NAME + " caractères.");
        else
            this.name = name;
    }

    private void setCountry(String country) throws TextLengthException {
        if(name.length() < LENGTH_MIN)
            throw new TextLengthException("Le nom du pays est trop court.\n Minimum " + LENGTH_MIN + " caractères.");
        else if(name.length() > LENGTH_NAME)
            throw new TextLengthException("Le nom du pays est trop long.\n Maximum " + LENGTH_NAME + " caractères.");
        else
            this.country = country;
    }
    //endregion
}
