package br.com.johndeere.servicos;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.PeoplesException;
import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;

public class ConsultPeoples {

	public PeopleVO consultPeoples(MovieVO film, String character_id) throws PeoplesException, IOException {
		
		PeopleVO people = null;
		String url = "";
		
//		List<String> lstPeople = film.getCharacters().stream().filter(s -> s.length() == peopleCode).collect(Collectors.toList());
		for(String value : film.getCharacters()) {
			if(value.contains("/people/" + character_id + "/")) {
				url = value;
				break;
			}
		}
		
		HttpsURLConnection conn = null;
		
		try {
			
			if(!url.equals("")) {
				conn = ConnectionBuilder.doConnection(url + "?format=json");
				people = ResponseBuilder.getPeopleResponse(ResponseBuilder.getResponse(conn));
			}
			
			if(people == null)
				throw new PeoplesException(
						"Personagem não encontrado para a identificação informada: " + character_id + ".");
			
		} catch (PeoplesException pe) {
			throw new PeoplesException(pe.getMessage());
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return people;
		
	}

}
