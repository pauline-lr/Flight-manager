package model;

import exception.PlaneException;

public class Plane {
    public final static int MODEL_LENGTH = 7;
    public final static int BRAND_LENGTH = 30;

    private String model;       // Max 7 characters
    private String brand;       // Max 30 characters

    //region Constructors
    public Plane(String model, String brand)
            throws PlaneException.BrandException, PlaneException.ModelException {
        setModel(model);
        setBrand(brand);
    }
    //endregion

    //region Setters
    private void setModel(String model)
            throws PlaneException.ModelException {
        if(model.length() <= MODEL_LENGTH)
            this.model = model;
        else
            throw new PlaneException.ModelException(model);
    }
    private void setBrand(String brand)
            throws PlaneException.BrandException {
        if(brand.length() <= BRAND_LENGTH)
            this.brand = brand;
        else
            throw new PlaneException.BrandException(brand);
    }
    //endregion

    //region Getters
    //endregion
}
