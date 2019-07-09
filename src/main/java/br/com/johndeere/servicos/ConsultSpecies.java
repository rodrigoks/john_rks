package br.com.johndeere.servicos;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.SpeciesException;
import br.com.johndeere.vos.PeopleVO;
import br.com.johndeere.vos.SpecieVO;

public class ConsultSpecies {

	public SpecieVO consultSpecies(PeopleVO people) throws SpeciesException, IOException {
		
		SpecieVO specie = null;
		String url = "";
		
//		List<String> lstPeople = film.getCharacters().stream().filter(s -> s.length() == peopleCode).collect(Collectors.toList());
		for(String value : people.getSpecies()) {
			url = value;
		}
		
		HttpsURLConnection conn = null;
		
		try {
			
			if(!url.equals("")) {
				conn = ConnectionBuilder.doConnection(url + "?format=json");
				specie = ResponseBuilder.getSpecieResponse(ResponseBuilder.getResponse(conn));
			}
			
			if(specie == null)
				throw new SpeciesException(
						"Espécie não encontrado para o personagem informado: " + people.getName() + ".");
			
		} catch (SpeciesException pe) {
			throw new SpeciesException(pe.getMessage());
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if(conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
		return specie;
		
	}

}
