package model;

public class Plane {
    private String model;       // Max 7 characters
    private Integer number;     // Max 3 positive digits
    private String brand;       // Max 30 characters

    //region Constructors
    public Plane(String model, Integer number, String brand) {
        setModel(model);
        setNumber(number);
        setBrand(brand);
    }
    //endregion

    //region Setters
    private void setModel(String model) {
        this.model = model;
    }
    private void setNumber(Integer number) {
        this.number = number;
    }
    private void setBrand(String brand) {
        this.brand = brand;
    }
    //endregion

    //region Getters
    //endregion
}
