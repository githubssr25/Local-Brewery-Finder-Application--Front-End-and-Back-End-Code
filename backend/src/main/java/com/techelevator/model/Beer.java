package com.techelevator.model;
public class Beer {

    private long id;
    private String name;
    private String description;
    private String beerType;
    private float abv;
    private String imageLink;
    private boolean glutenFree = false;
    private long breweryId;

    public Beer() {
    }

    public Beer(long id, String name, String description, String beerType, float abv, String imageLink, boolean glutenFree, long breweryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.beerType = beerType;
        this.abv = abv;
        this.imageLink = imageLink;
        this.glutenFree = true;
        this.breweryId = breweryId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(long breweryId) {
        this.breweryId = breweryId;
    }
}
