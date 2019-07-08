package br.com.johndeere.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import br.com.johndeere.vos.FilmVO;

public abstract class ResponseBuilder {

	private static Gson gson;
	
	public static FilmVO getFilmResponse(String json) throws IOException {
		
		if(gson == null)
			gson = new Gson();
		
		FilmVO film = gson.fromJson(json, FilmVO.class);
		return film;
		
    }
	
	public static String getResponse(HttpsURLConnection conn) throws IOException {
		
		String inputLine;
		StringBuffer content = new StringBuffer();
		BufferedReader in = null;
		
		try {
			
			if(conn.getResponseCode() > 299) {
				
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
