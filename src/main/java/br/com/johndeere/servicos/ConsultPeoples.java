package br.com.johndeere.servicos;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.MoviesException;
import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;

public class ConsultPeoples {

	public PeopleVO consultarFilmes(MovieVO film, String character_id) throws MoviesException, IOException {
		
		if(character_id.equals(""))
			throw new MoviesException("Favor informar um valor para character_id");
		
		if(film == null)
			throw new MoviesException("Filme não encontrado");
		
		int peopleCode = Integer.valueOf(character_id).intValue();
		PeopleVO people = null;
		String url = "";
		
//		List<String> lstPeople = film.getCharacters().stream().filter(s -> s.length() == peopleCode).collect(Collectors.toList());
		int count = 1;
		for(String value : film.getCharacters()) {
			if(peopleCode == count) {
				url = value;
				break;
			}
			count++;
		}
		
		HttpsURLConnection conn = null;
		
		try {
			
			conn = ConnectionBuilder.doConnection(url + "?format=json");
			people = ResponseBuilder.getPeopleResponse(ResponseBuilder.getResponse(conn));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return people;
		
	}

}
