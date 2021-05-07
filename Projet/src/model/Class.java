package model;

import exception.NameClassException;

public class Class {
    private String name;    // Max 20 characters

    //region Constructors
    public Class(String name) throws NameClassException {
        setName(name);
    }
    //endregion

    //region Setters
    private void setName(String name) throws NameClassException {
        if(name.length() <= 50)
            this.name = name;
        else
            throw new NameClassException(name);
    }
    //endregion

    //region Getters
    //endregion
}
