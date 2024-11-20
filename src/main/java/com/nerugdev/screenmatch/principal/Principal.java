package com.nerugdev.screenmatch.principal;

import com.nerugdev.screenmatch.model.DatosEpisodio;
import com.nerugdev.screenmatch.model.DatosSerie;
import com.nerugdev.screenmatch.model.DatosTemporadas;
import com.nerugdev.screenmatch.model.Episodio;
import com.nerugdev.screenmatch.service.ConsumoAPI;
import com.nerugdev.screenmatch.service.ConvierteDatos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c7700e77";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");
        //Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas as temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas() ; i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
 //       temporadas.forEach(System.out::println);

        //Mostrar solos el título de los episodios para las temporadas
/*        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        //Usando funciones lambda
//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo DatosEpisodio

        List<DatosEpisodio> datosEpisodios = temporadas.stream().
                flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        //Top 5 episodios
/*        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer filtor (N/A) " + e))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segundo filtro ordenación (M>m) " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercer filtro mayúscula (m>M) " + e))
                .limit(5)
                .forEach(System.out::println);*/

        //Convirtiendo los datos a una lista del tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                    .map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

        //episodios.forEach(System.out::println);

        // Búsqueda de episodios a partir de x año
/*         System.out.println("Por favor indica el año a partir del cual deseas ver los episodios");
        var fecha = teclado.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(Integer.parseInt(fecha), 1, 1);

       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e  -> System.out.println(
                        "Temporada " + e.getTemporada() +
                                "Episodio " + e.getTitulo() +
                                "Fecha de lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
                ));*/

        // Busca episodio por un pedazo de titulo
/*        System.out.println("Por favor escriba el titulo del episodio que desea ver");
        var pedazoTituo  = teclado.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTituo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado");
            System.out.println("Los datos son: " + episodioBuscado.get());
        }else{
            System.out.println("Episodio no encontrado");
        }*/
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);
    }
}
