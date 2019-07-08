package br.com.johndeere.servicos;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.MoviesException;
import br.com.johndeere.vos.MovieVO;

public class ConsultMovie {

	public MovieVO consultMovies(String filmId) throws MoviesException, IOException {
		
		if(filmId.equals(""))
			throw new MoviesException("Favor informar um valor para filmId");
		
		String url = "https://swapi.co/api/films/" + filmId + "/?format=json";
		HttpsURLConnection conn = null;
		MovieVO movie = null;
		
		try {
			
			conn = ConnectionBuilder.doConnection(url);
			movie = ResponseBuilder.getMovieResponse(ResponseBuilder.getResponse(conn));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return movie;
		
	}

}
