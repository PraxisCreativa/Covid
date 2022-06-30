package com.covid.model;

import java.time.LocalDate;

public class Infectados {

    private String ciudad;
    private int cantidadInfectados;
    private LocalDate fecha;



    public Infectados(String ciudad, int cantidadInfectados, LocalDate fecha) {
        this.ciudad = ciudad;
        this.cantidadInfectados = cantidadInfectados;
        this.fecha = fecha;
    }
    public Infectados(){

    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCantidadInfectados() {
        return cantidadInfectados;
    }

    public void setCantidadInfectados(int cantidadInfectados) {
        this.cantidadInfectados = cantidadInfectados;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


}
