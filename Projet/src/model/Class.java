package model;

import exception.NameClassException;

public class Class {
    public final static int NAME_LENGTH = 20;

    private String name;    // Max 20 characters

    //region Constructors
    public Class(String name) throws NameClassException {
        setName(name);
    }
    //endregion

    //region Setters
    private void setName(String name) throws NameClassException {
        if(name.length() <= NAME_LENGTH)
            this.name = name;
        else
            throw new NameClassException(name);
    }
    //endregion

    //region Getters
    //endregion
}
