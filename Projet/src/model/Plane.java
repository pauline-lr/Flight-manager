package model;

public class Plane {
    private String model;       // Max 7 characters
    private String brand;       // Max 30 characters

    //region Constructors
    public Plane(String model, String brand) {
        setModel(model);
        setBrand(brand);
    }
    //endregion

    //region Setters
    private void setModel(String model) {
        this.model = model;
    }
    private void setBrand(String brand) {
        this.brand = brand;
    }
    //endregion

    //region Getters
    //endregion
}
