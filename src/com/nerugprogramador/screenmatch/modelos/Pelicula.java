package com.nerugprogramador.screenmatch.modelos;

public class Pelicula {
    public String nombre;
    int fechaDeLanzamiento;
    int duracionEnMinutos;
    boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeLasEvaluaciones;

    public int getTotalDeLasEvaluaciones(){
        return totalDeLasEvaluaciones;
    }

    public void nuestraFichaTecnica(){
        System.out.println("El nombre de película es: " + nombre);
        System.out.println("Su fecha de lanzamientos es: " + fechaDeLanzamiento);
        System.out.println("Duración en minutos: " + duracionEnMinutos);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeLasEvaluaciones++;
    }

    public double calculaMedia(){
        return sumaDeLasEvaluaciones / totalDeLasEvaluaciones;
    }
}
