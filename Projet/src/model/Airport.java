package model;

import exception.CodeException;
import exception.NameException;
import exception.TerminalException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Airport {
    private String code;        // 3 letters
    private String name;        // Max 50 characters
    private String country;     // Max 50 characters

    //region Constructors
    public Airport(String code, String name, String country)
            throws CodeException, NameException {
        setCode(code);
        setName(name);
        setCountry(country);
    }
    //endregion

    //region Setters
    private void setCode(String code) throws CodeException {
        String patternCode = "^\\w{3}$";
        Pattern r = Pattern.compile(patternCode);
        Matcher m = r.matcher(code);
        if (m.find())
            this.code = code;
        else
            throw new CodeException(code);
    }
    private void setName(String name) throws NameException {
        if(name.length() <= 50)
            this.name = name;
        else
            throw new NameException(name);
    }
    private void setCountry(String country) throws NameException {
        if(name.length() <= 50)
            this.name = name;
        else
            throw new NameException(name);
    }
    //endregion

    //region Getters
    //endregion
}
