package br.com.johndeere.servicos;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.johndeere.constantes.JdConstantes;

public abstract class ConnectionBuilder {

	public static HttpsURLConnection doConnection(String urlPath) throws IOException {
		
		URL url;
		HttpsURLConnection conn = null;
		
		try {
			
			url = new URL(urlPath);
			conn = (HttpsURLConnection) url.openConnection();
			conn.addRequestProperty(JdConstantes.USER_AGENT, JdConstantes.USER_AGENT_VALUE);
			conn.setRequestMethod(JdConstantes.GET);
			conn.setRequestProperty(JdConstantes.CONTENT_TYPE, JdConstantes.CONTENT_TYPE_JSON_VALUE);
			conn.setConnectTimeout(JdConstantes.CONNECTION_TIMEOUT);
			conn.setReadTimeout(JdConstantes.READ_TIMEOUT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
