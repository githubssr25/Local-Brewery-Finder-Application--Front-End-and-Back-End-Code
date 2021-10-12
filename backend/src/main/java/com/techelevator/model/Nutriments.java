package com.techelevator.model;

public class Nutriments {

    private double alcohol;
    private String alcohol_unit;

    public Nutriments() {

    }

    public Nutriments(double alcohol, String alcohol_unit) {
        this.alcohol = alcohol;
        this.alcohol_unit = alcohol_unit;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public String getAlcohol_unit() {
        return alcohol_unit;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public void setAlcohol_unit(String alcohol_unit) {
        this.alcohol_unit = alcohol_unit;
    }
}
