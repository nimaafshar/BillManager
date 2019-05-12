package models;

import models.enums.FitType;

public class Pants extends Cloth{
    public FitType fitType;

    public Pants(Cloth cloth,FitType fitType) {
        super(cloth);
        this.fitType = fitType;
    }
}
