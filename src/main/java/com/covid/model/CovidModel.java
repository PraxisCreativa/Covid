package com.covid.model;

public class CovidModel {
    private String pais;
    private String region;
    private String ciudad;
    private int poblacion;

    public CovidModel(String pais, String region, String ciudad, int poblacion) {
        super();
        this.pais = pais;
        this.region = region;
        this.ciudad = ciudad;
        this.poblacion = poblacion;
    }

    public CovidModel(Infectados infectados){

    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }
}
