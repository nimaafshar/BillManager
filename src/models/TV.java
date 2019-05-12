package models;

import javafx.scene.paint.Color;
import models.enums.EnergyLabel;
import models.enums.ScreenType;

public class TV extends ElectricalProduct {

    public boolean homeCinema;
    public ScreenType screenType;

    public TV(ElectricalProduct electricalProduct,ScreenType screenType,boolean homeCinema) {
        super(electricalProduct);
        this.screenType = screenType;
        this.homeCinema =homeCinema;
    }
}
