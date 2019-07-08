package br.com.johndeere.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.exceptions.FilmesException;

public class ConsultarFilmes {

	private String filmId;
	
	public ConsultarFilmes() {
	}
	
	public void consultarFilmes(String filmId) throws FilmesException {
		
		if(filmId.equals(""))
			throw new FilmesException("Favor informar um valor para filmId");
		
		URL url;
		HttpsURLConnection conn;
		String site;
		
		try {
			
			site = "https://swapi.co/api/films/" + filmId + "/";
			url = new URL(site);
			conn = (HttpsURLConnection) url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
        String httpsURL = "https://swapi.co/api/films/1/";
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            System.out.println(inputLine);
        }

        br.close();
    }
	
	public String getFilmId() {
		return filmId;
	}

	public void setFilmId(String filmId) {
		this.filmId = filmId;
	}

}
