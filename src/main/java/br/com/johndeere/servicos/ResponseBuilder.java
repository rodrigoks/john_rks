package br.com.johndeere.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;
import br.com.johndeere.vos.SpecieVO;

public abstract class ResponseBuilder {

	private static Gson gson;
	
	public static MovieVO getMovieResponse(String json) throws IOException {
		
		if(gson == null)
			gson = new Gson();
		
		MovieVO movie = gson.fromJson(json, MovieVO.class);
		return movie;
		
    }

	public static PeopleVO getPeopleResponse(String json) throws IOException {
		
		if(gson == null)
			gson = new Gson();
		
		PeopleVO people = gson.fromJson(json, PeopleVO.class);
		return people;
		
    }

	public static SpecieVO getSpecieResponse(String json) throws IOException {
		
		if(gson == null)
			gson = new Gson();
		
		SpecieVO specie = gson.fromJson(json, SpecieVO.class);
		return specie;
		
    }
	
	public static String getResponse(HttpsURLConnection conn) throws IOException {
		
		String inputLine;
		StringBuffer content = new StringBuffer();
		BufferedReader in = null;
		
		try {
			
			if(conn.getResponseCode() < 299) {
				
				in = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				
				content = new StringBuffer();
				
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}

			}
			
		} catch (Exception e) {
		} finally {
			if(in != null) {
				in.close();
				in = null;
			}
		}
		
		return content.toString();
		
	}
	
}
