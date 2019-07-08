package br.com.johndeere.servicos;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.MoviesException;
import br.com.johndeere.vos.MovieVO;

public class ConsultMovie {

	public MovieVO consultMovies(String filmId) throws MoviesException, IOException {
		
		String url = "https://swapi.co/api/films/" + filmId + "/?format=json";
		HttpsURLConnection conn = null;
		MovieVO movie = null;
		
		try {
			
			conn = ConnectionBuilder.doConnection(url);
			movie = ResponseBuilder.getMovieResponse(ResponseBuilder.getResponse(conn));
			if(movie == null)
				throw new MoviesException(
						"Filme não encontrado para a identificação informada: " + filmId + ".");
			
		} catch (MoviesException me) {
			throw new MoviesException(me.getMessage());
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return movie;
		
	}

}
