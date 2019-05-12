package models;

import models.enums.MobileOS;

public class Mobile extends ElectricalProduct {
    int storage;//in gigabytes
    MobileOS mobileOS;
    public Mobile(ElectricalProduct electricalProduct,MobileOS mobileOS,int storage) {
        super(electricalProduct);
        this.mobileOS = mobileOS;
        this.storage =storage;
    }
}
