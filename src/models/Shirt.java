package models;

import models.enums.Sleeve;

public class Shirt extends Cloth{
    public Sleeve sleeve;

    public Shirt(Cloth cloth,Sleeve sleeve) {
        super(cloth);
        this.sleeve = sleeve;
    }
}
