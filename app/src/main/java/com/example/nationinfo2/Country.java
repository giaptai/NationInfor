package com.example.nationinfo2;

public class Country {
    String img;
    String name;
    String nameID;
    Integer population;
    Double areaInSqKm;
    String imgMap;

    public Country(String img, String name, String nameID, Integer population, Double areaInSqKm, String imgMap) {
        this.img = img;
        this.name = name;
        this.nameID = nameID;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.imgMap = imgMap;
    }

    public String getImgMap() {
        return imgMap;
    }

    public void setImgMap(String imgMap) {
        this.imgMap = imgMap;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(Double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

