package com.nerugprogramador.screenmatch.calculos;
import com.nerugprogramador.screenmatch.modelos.Pelicula;

public class CalculadoraDeTiempo {
    private int tiempoTotal;

    public void incluye(Pelicula pelicula){
        tiempoTotal += pelicula.getDuracionEnMinutos();
    }
}
