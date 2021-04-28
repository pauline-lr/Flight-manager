package model;

public class Class {
    private String name;    // Max 20 characters

    //region Constructors
    public Class(String name) {
        setName(name);
    }
    //endregion

    //region Setters
    private void setName(String name) {
        this.name = name;
    }
    //endregion

    //region Getters
    //endregion
}
