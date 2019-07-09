package br.com.johndeere.servicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;
import br.com.johndeere.vos.SpecieVO;

public class RetrieveSpeciesFromMovie {

	public Collection<PeopleVO> retrievePeopleBySpecie(
			MovieVO movie, 
			PeopleVO people) throws IOException {
		
		HttpsURLConnection conn = null;
		PeopleVO peopleTmp;
		Collection<PeopleVO> colPeople = new ArrayList<PeopleVO>();
		
		for(String url : movie.getCharacters()) {
			conn = ConnectionBuilder.doConnection(url + "?format=json");
			peopleTmp = ResponseBuilder.getPeopleResponse(ResponseBuilder.getResponse(conn));
			if(peopleTmp.getSpecies().contains(people.getSpecies().get(0))) {
				colPeople.add(peopleTmp);
			}
		}
		return colPeople;
	}
}
