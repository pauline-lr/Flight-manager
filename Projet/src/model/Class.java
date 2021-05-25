package model;

import exception.NameClassException;

public class Class {
    private Integer classID;
    private String name;    // Max 20 characters

    //region Constructors
    public Class(Integer classID, String name) throws NameClassException {
        setClassID(classID);
        setName(name);
    }
    //endregion

    //region Setters
    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    private void setName(String name) throws NameClassException {
        if(name.equals("Première") || name.equals("Affaire") ||name.equals("Économique"))
            this.name = name;
        else
            throw new NameClassException(name);
    }
    //endregion

    //region Getters
    public String getName() {
        return name;
    }
    //endregion
}
