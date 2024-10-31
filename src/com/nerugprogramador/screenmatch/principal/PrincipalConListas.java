package com.nerugprogramador.screenmatch.principal;

import com.nerugprogramador.screenmatch.modelos.Pelicula;
import com.nerugprogramador.screenmatch.modelos.Serie;
import com.nerugprogramador.screenmatch.modelos.Titulo;

import java.util.ArrayList;

public class PrincipalConListas {
    public static void main(String[] args) {

        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Matrix", 1998);
        miPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("El señor de los anillos", 2001);
        miPelicula.evalua(10);
        Serie casaDragon = new Serie("La casa del dragón", 2022 );

        Pelicula p1 = miPelicula;

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(casaDragon);


        for (Titulo item: lista){
            System.out.println(item.getNombre());
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
                System.out.println(pelicula.getClasificacion());
            }
        }

    }
}
