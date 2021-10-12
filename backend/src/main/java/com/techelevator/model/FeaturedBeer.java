package com.techelevator.model;



public class FeaturedBeer {
    private String brands;
    private String product_name;
    private String image_url;
//    private String allergens;
    private long _id;
    private Nutriments nutriments;
    private double alcohol;
    private String alcohol_unit;

    public FeaturedBeer() {

    }

    public FeaturedBeer(String brands, String product_name, String image_url, long _id, Nutriments nutriments) {
        this.brands = brands;
        this.product_name = product_name;
        this.image_url = image_url;
        this._id = _id;
        this.nutriments = nutriments;
        this.alcohol = nutriments.getAlcohol();
        this.alcohol_unit = nutriments.getAlcohol_unit();
    }

    public String getBrands() {
        return brands;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public long get_id() {
        return _id;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public String getAlcohol_unit() {
        return alcohol_unit;
    }



}

