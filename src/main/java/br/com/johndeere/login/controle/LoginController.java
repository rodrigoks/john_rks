package br.com.johndeere.login.controle;

import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import br.com.johndeere.login.constantes.LoginConstantes;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController extends HttpServlet {

    /**
     * UID Serial Version
     */
    private static final long serialVersionUID = 1L;

    private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("Usuario e senha nao preenchidos.").build();
    private static final Response ACCESS_AUTHORIZED = Response.status(Response.Status.ACCEPTED).build();

    /**
     * Metodo responsavel por logar o usuario e coloca-lo na sessao.
     *
     * @param request
     * @return
     */
    @POST
    @PermitAll
    public static Response login(@Context HttpServletRequest request) {

        // Recupera a informacao de token do header da requisicao
        final String token = request.getHeader(LoginConstantes.TOKEN_PROPERTY);

		/*
		 * Se existir token aceita a solicitacao.
		 */
        if(token != null && !token.equals(""))
            return ACCESS_AUTHORIZED;

        // Recupera os dados do header da requisicao
        final String authorization = request.getHeader(LoginConstantes.AUTHORIZATION_PROPERTY);

		/*
		 * Se nao existir uma propriedade de autorizacao bloqueia acesso ao
		 * servico.
		 */
        if (authorization == null || authorization.isEmpty())
            return ACCESS_UNAUTHORIZED;

        // Recupera o token de autorizacao Base64
        final String encodedUserPassword = authorization.replaceFirst(LoginConstantes.AUTHENTICATION_SCHEME + " ", "");

        // Decodifica usuario e senha
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

        // Recupera usuario e senha do token
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        try {
        	
        	System.out.println("Username: " + username);
        	System.out.println("Password: " + password);

        } catch (Exception e){
//            return Response.status(Response.Status.UNAUTHORIZED).entity(
//                    new ResponseServiceVO(
//                            Response.Status.UNAUTHORIZED.getStatusCode(),
//                            Response.Status.UNAUTHORIZED,
//                            e.getMessage(),
//                            null)).build();
        	return Response.status(Response.Status.BAD_REQUEST).build();
        }

//        return Response.ok(
//                new AuthVO(
//                        jUser.getId(),
//                        jUser.getName(),
//                        jUser.getAuthorize().getLogin(),
//                        jUser.getEmail(),
//                        jUser.getGroup().getId(),
//                        jUser.getGroup().getGroup(),
//                        JdConstantes.SECRET_CODE,
//                        jUser.getAuthorize().isFirstAccess()),
//                MediaType.APPLICATION_JSON).build();
        return Response.ok().build();

    }

    /**
     * Metodo responsavel por efetuar o logout da aplicacao.
     *
     * @return
     */
    public void logOut() {}

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
