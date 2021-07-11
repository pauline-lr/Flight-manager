package model;

import exception.TextLengthException;

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
            throws TextLengthException {
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
            throws TextLengthException {
        if(model.length() < MODEL_LENGTH_MIN)
            throw new TextLengthException("Le nom du modèle de l'avion est trop court. Minimum " + MODEL_LENGTH_MIN + "caractères");
        else if (model.length() > MODEL_LENGTH_MAX)
            throw new TextLengthException("Le nom du modèle de l'avion est long court. Maximum " + MODEL_LENGTH_MAX + "caractères");
        this.model = model;

    }
    private void setBrand(String brand)
            throws TextLengthException {
        if(brand.length() < BRAND_LENGTH_MIN)
            throw new TextLengthException("Le nom du la marque de l'avion est trop court. Minimum " + BRAND_LENGTH_MIN + "caractères");
        else if (brand.length() > BRAND_LENGTH_MAX)
            throw new TextLengthException("Le nom de la marque de l'avion est long court. Maximum " + BRAND_LENGTH_MAX + "caractères");
        this.brand = brand;
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
