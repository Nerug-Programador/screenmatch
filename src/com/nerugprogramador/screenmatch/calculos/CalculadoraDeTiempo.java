package com.nerugprogramador.screenmatch.calculos;
import com.nerugprogramador.screenmatch.modelos.Pelicula;
import com.nerugprogramador.screenmatch.modelos.Serie;
import com.nerugprogramador.screenmatch.modelos.Titulo;

public class CalculadoraDeTiempo {
    private int tiempoTotal;

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void incluye(Titulo titulo){
        this.tiempoTotal += titulo.getDuracionEnMinutos();
    }

}