package model;

import exception.PlaneException;

public class Plane {
    private final static int MODEL_LENGTH_MIN = 2;
    private final static int MODEL_LENGTH_MAX = 7;
    private final static int BRAND_LENGTH_MIN = 2;
    private final static int BRAND_LENGTH_MAX = 30;

    private Integer planeId;
    private String model;       // Max 7 characters
    private String brand;       // Max 30 characters

    //region Constructors
    public Plane(Integer planeId,String model, String brand)
            throws PlaneException.BrandException, PlaneException.ModelException {
        setPlaneId(planeId);
        setModel(model);
        setBrand(brand);
    }
    //endregion

    //region Setters
    public void setPlaneId(Integer planeId) {
        this.planeId = planeId;
    }

    private void setModel(String model)
            throws PlaneException.ModelException {
        if(model.length() >= MODEL_LENGTH_MIN && model.length() <= MODEL_LENGTH_MAX)
            this.model = model;
        else
            throw new PlaneException.ModelException(model);
    }
    private void setBrand(String brand)
            throws PlaneException.BrandException {
        if(brand.length() >= BRAND_LENGTH_MIN && brand.length() <= BRAND_LENGTH_MAX)
            this.brand = brand;
        else
            throw new PlaneException.BrandException(brand);
    }
    //endregion

    //region Getters
    public String getName(){
        return model + " " + brand;
    }

    public Integer getPlaneId() {
        return planeId;
    }
    //endregion
}
