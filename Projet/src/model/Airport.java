package model;

public class Airport {
    private String code;        // 3 letters
    private String name;        // Max 50 characters
    private String country;     // Max 50 characters

    //region Constructors
    public Airport(String code, String name, String country) {
        setCode(code);
        setName(name);
        setCountry(country);
    }
    //endregion

    //region Setters
    private void setCode(String code) {
        this.code = code;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setCountry(String country) {
        this.country = country;
    }
    //endregion

    //region Getters
    //endregion
}
