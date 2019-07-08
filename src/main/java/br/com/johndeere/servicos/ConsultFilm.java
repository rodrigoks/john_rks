package br.com.johndeere.servicos;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.FilmsException;
import br.com.johndeere.vos.FilmVO;

public class ConsultFilm {

	public FilmVO consultarFilmes(String filmId) throws FilmsException, IOException {
		
		if(filmId.equals(""))
			throw new FilmsException("Favor informar um valor para filmId");
		
		URL url;
		HttpsURLConnection conn = null;
		FilmVO film = null;
		
		try {
			
			url = new URL("https://swapi.co/api/films/" + filmId + "/?format=json");
			conn = (HttpsURLConnection) url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			
			film = ResponseBuilder.getFilmResponse(ResponseBuilder.getResponse(conn));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return film;
		
	}

}
