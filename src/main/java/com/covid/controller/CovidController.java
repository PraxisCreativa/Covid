package com.covid.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import com.covid.model.CovidModel;
import com.covid.model.Infectados;

@RestController
public class CovidController {

    private List<CovidModel> ciudades;
    private List<Infectados> infectadosLista;
    @PostConstruct
    public void init(){

        /* INICIALIZAMOS CON UN ARRAYLIST LAS CIUDADES */

        ciudades= new ArrayList<>();
        /* Andalucía  */
        ciudades.add(new CovidModel("España","Andalucía", "Almería", 200753));
        ciudades.add(new CovidModel("España","Andalucía", "Cadiz", 114244));
        ciudades.add(new CovidModel("España","Andalucía", "Córdoba", 325708));
        ciudades.add(new CovidModel("España","Andalucía", "Granada", 232208));
        ciudades.add(new CovidModel("España","Andalucía", "Huelva", 144258));
        ciudades.add(new CovidModel("España","Andalucía", "Jaén", 113457 ));
        ciudades.add(new CovidModel("España","Andalucía", "Málaga ", 569005));
        ciudades.add(new CovidModel("España","Andalucía", "Sevilla", 688711));
        /*  Aragón  */
        ciudades.add(new CovidModel("España","Aragón", "Huesca", 52463));
        ciudades.add(new CovidModel("España","Aragón", "Teruel ", 35691));
        ciudades.add(new CovidModel("España","Aragón", "Zaragoza", 666880));
        /* Cataluña */
        ciudades.add(new CovidModel("España","Cataluña", "Barcelona", 560000));
        ciudades.add(new CovidModel("España","Cataluña", "Girona", 100266));
        ciudades.add(new CovidModel("España","Cataluña", "Lleida ", 435000));
        ciudades.add(new CovidModel("España","Cataluña", "Tarragona", 132299));
        /* Madrid */
        ciudades.add(new CovidModel("España","Madrid", "Madrid", 650000));
        /* Comunidad Valenciana */
        ciudades.add(new CovidModel("España","Comunidad Valenciana", "Alicante", 331577));
        ciudades.add(new CovidModel("España","Comunidad Valenciana", "Castellón ", 571601));
        ciudades.add(new CovidModel("España","Comunidad Valenciana", "Valencia ", 791413));

        /* INICIALIZAMOS CON UN ARRAYLIST LOS INFECTADOS */

        infectadosLista= new ArrayList<>();
        infectadosLista.add(new Infectados("Madrid",850, LocalDate.now()));

    }

/*LISTAR CIUDADES */
   @GetMapping(value = "regiones", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<CovidModel> getCiudades(){return ciudades;}

/* DAR DE ALTA UNA REGION/CIUDAD/PAIS  */
   @PostMapping(value="altaRegion", consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<CovidModel> altaRegion(@RequestBody CovidModel covidModel){
        ciudades.add(covidModel);
        return ciudades;
   }

/*LISTAR INFECTADOS */
    @GetMapping(value = "infectados", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Infectados> getInfectadosLista(){return infectadosLista;}


/* REGISTRAR INFECTADOS EN UNA FECHA CONCRETA */
    @PostMapping(value = "altaInfectados", produces = MediaType.APPLICATION_JSON_VALUE)
    public String altaInfectados(@RequestBody Infectados infectados){

     String respuesta = null;
     boolean existe;
     boolean encontrado = false;

        for (int x = 0; x < ciudades.size(); x++) {
            CovidModel ciclo = ciudades.get(x);
            if (ciclo.getCiudad().equals(infectados.getCiudad())) {
                encontrado = true;
                break;
            }
        }
       //Verificamos si la ciudad fue encontrada o no para registrar en numero de infectados en un dia equis
        if (encontrado) {
            infectadosLista.add(infectados);
            respuesta ="Registro Exitoso";

        } else {
            respuesta ="ERROR - la ciudad no existe debe darse de alta antes de poder registrar por este método.";
           // respuesta = String.valueOf(infectados.getCiudad());
        }

        return respuesta;
    }


    /* FUNCION PARA CALCULAR  LA TASA DE INCIDENCIA DE CORONA VIRUS */

    public int calcularTasa(int infectados, String ciudad){
        int poblacionCal = 0;

        //obtenemos el numero de la poblacion en base a la ciudad registrada
        for (int x = 0; x < ciudades.size(); x++) {
            CovidModel ciclo = ciudades.get(x);
            if (ciclo.getCiudad().equals(ciudad)){
                poblacionCal = ciclo.getPoblacion();
                break;
            }
        }

        int calculo = (poblacionCal * infectados)/100000;

        return calculo;
    }


    /* FUNCION PARA DEVOLVER TASA DE INCIDENCIA PASANDO COMO PARAMETROS CIUDAD Y FECHA  */

    @PostMapping(value = "tasaIncidencia", produces = MediaType.APPLICATION_JSON_VALUE)
    public String tasaIncidencia(@RequestBody Infectados infectados){

        String respuesta2 = null;
        Integer respuestaF = null;
        boolean existe;
        boolean encontrado = false;
        String ciudadEncontrado = null;
        String fechaEncontrado = null;
        int infectadoEncontrado = 0;

        //Verificamos si coincide la ciudad y la fecha en los registros de memoria de infectados
        for (int x = 0; x < infectadosLista.size(); x++) {
            Infectados ciclo = infectadosLista.get(x);
            if (ciclo.getCiudad().equals(infectados.getCiudad()) && ciclo.getFecha().equals(infectados.getFecha())) {
                encontrado = true;
                ciudadEncontrado = ciclo.getCiudad();
                fechaEncontrado = String.valueOf(ciclo.getFecha());
                infectadoEncontrado = ciclo.getCantidadInfectados();
                break;
            }
        }

        if (encontrado) {
        respuesta2 ="En la ciudad de " + ciudadEncontrado + " con fecha " + fechaEncontrado + " existe la siguiente cantidad de infectado: " +infectadoEncontrado + " de lo cual su tasa de incidencia por cada 100.000 habitantes se encuentra en: " + calcularTasa(infectadoEncontrado, ciudadEncontrado) + " Puntos";
        } else {
            respuesta2 ="ERROR - la ciudad no existe debe darse de alta antes de poder registrar por este método.";
               }

        return respuesta2;
    }




}
