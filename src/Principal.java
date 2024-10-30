import com.nerugprogramador.screenmatch.calculos.CalculadoraDeTiempo;
import com.nerugprogramador.screenmatch.calculos.FiltroRecomendacion;
import com.nerugprogramador.screenmatch.modelos.Episodio;
import com.nerugprogramador.screenmatch.modelos.Pelicula;
import com.nerugprogramador.screenmatch.modelos.Serie;

import javax.swing.text.FlowView;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.setDuracionEnMinutos(120);
        miPelicula.setIncluidoEnElPlan(true);

        miPelicula.nuestraFichaTecnica();
        miPelicula.evalua(10);
        miPelicula.evalua(10);
        miPelicula.evalua(7.8);
        System.out.println(miPelicula.getTotalDeLasEvaluaciones());
        System.out.println(miPelicula.calculaMedia());

        Serie casaDragon = new Serie("La casa del dragón", 2022 );
        casaDragon.setTemporadas(1);
        casaDragon.setMinutosPorEpisodios(50);
        casaDragon.setEpisodiosPorTemporada(10);
        casaDragon.nuestraFichaTecnica();
        System.out.println(casaDragon.getDuracionEnMinutos());

        Pelicula otraPelicula = new Pelicula("Matrix", 1998);
        otraPelicula.setFechaDeLanzamiento(1998);
        otraPelicula.setDuracionEnMinutos(180);


        CalculadoraDeTiempo caluladora = new CalculadoraDeTiempo();
        caluladora.incluye(miPelicula);
        caluladora.incluye(casaDragon);
        caluladora.incluye(otraPelicula);
        System.out.println("tiempo necesario para ver tus títulos favoritos estas vacaciones " + caluladora.getTiempoTotal() + " minutos.");

        FiltroRecomendacion filtro = new FiltroRecomendacion();
        filtro.filtra(miPelicula);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(casaDragon.getNombre());
        episodio.setTotalVisualizaciones(300);
        filtro.filtra(episodio);

        var peliculaDeBruno = new Pelicula("El señor de los anillos", 2001);
        peliculaDeBruno.setDuracionEnMinutos(180);
        peliculaDeBruno.setFechaDeLanzamiento(2001);

        ArrayList<Pelicula> listDePeliculas = new ArrayList<>();
        listDePeliculas.add(peliculaDeBruno);
        listDePeliculas.add(miPelicula);
        listDePeliculas.add(otraPelicula);

        System.out.println("Tamaño de la lista: " + listDePeliculas.size());
        System.out.println("La primera pelicula es: " + listDePeliculas.get(0).getNombre());
        System.out.println(listDePeliculas.toString());

        System.out.println("toString de la pelicula: " + listDePeliculas.get(0).toString());
    }
}
