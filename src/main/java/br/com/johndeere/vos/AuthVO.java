package br.com.johndeere.vos;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import br.com.johndeere.constantes.JdConstantes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthVO {
	
   private static final long serialVersionUID = 1L;

   @Expose
   private String login;
   @Expose
   private Timestamp expires = gerarTempoExpiracao();
   @Expose
   private String token;

   /**
    * Construtor com argumento.
    *
    * @param login
    * @param secretCode
    */
   public AuthVO(
           String login,
           String secretCode){
       this.login = login;
       if(secretCode != null && !"".equals(secretCode))
    	   this.token = gerarToken(secretCode);
   }

   /**
    * Gerar um token especifico para este usuario.
    *
    * @return
    */
   private String gerarToken(String secretCode) {
       Gson gson = new Gson();
       String strToken = Jwts.builder().setSubject(gson.toJson(this)).signWith(SignatureAlgorithm.HS512, secretCode).compact();
       return strToken;
   }

   /**
    * Gerar o tempo de expiracao do token.
    *
    * @return
    */
   private Timestamp gerarTempoExpiracao(){
       GregorianCalendar gc = new GregorianCalendar();
       gc.add(Calendar.HOUR, JdConstantes.TEMPO_LOGIN);
       return new Timestamp(gc.getTimeInMillis());
   }

   /**
    * @return the token
    */
   public String getToken() {
       return token;
   }
   /**
    * @param token the token to set
    */
   public void setToken(String token) {
       this.token = token;
   }
   /**
    * @return the serialversionuid
    */
   public static long getSerialversionuid() {
       return serialVersionUID;
   }

   /**
    * @return the login
    */
   public String getLogin() {
       return login;
   }

   /**
    * @param login the login to set
    */
   public void setLogin(String login) {
       this.login = login;
   }

   /**
    * @return the expires
    */
   public Timestamp getExpires() {
       return expires;
   }

   /**
    * @param expires the expires to set
    */
   public void setExpires(Timestamp expires) {
       this.expires = expires;
   }

}
