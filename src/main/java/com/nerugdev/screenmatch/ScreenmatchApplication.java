package com.nerugdev.screenmatch;

import com.nerugdev.screenmatch.model.DatosEpisodio;
import com.nerugdev.screenmatch.model.DatosSerie;
import com.nerugdev.screenmatch.service.ConsumoAPI;
import com.nerugdev.screenmatch.service.ConvirerteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game%20of%20thrones&apikey=c7700e77");
//		var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConvirerteDatos conversor = new ConvirerteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);
		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game%20of%20thrones&Season=1&episode=1&apikey=c7700e77");
		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodios);
	}
}
