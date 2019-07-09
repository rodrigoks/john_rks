package br.com.johndeere.servlet.filters;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

import br.com.johndeere.constantes.JdConstantes;
import br.com.johndeere.constantes.LoginConstantes;
import br.com.johndeere.vos.AuthVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * Created by jbusiness on 30/03/17.
 */
@Singleton
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    @Context
    private HttpServletRequest request;

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container
     * .ContainerRequestContext)
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {

        if(requestContext.getMethod().equalsIgnoreCase("OPTIONS")){
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }

        Method method = resourceInfo.getResourceMethod();

		/*
		 * Verifica se o servico tem acesso publico, se tiver nao e necessario a
		 * validacao do token.
		 */
        if (!method.isAnnotationPresent(PermitAll.class)) {

			/*
			 * Verifica se o acesso esta negado para todos. Se estiver retorna
			 * acesso bloqueado e para a validacao.
			 */
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }

            // Recupera os dados do header da requisicao
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            
            // Recupera a autorizacao do header da requisicao.
            final List<String> lstToken = headers.get(LoginConstantes.TOKEN_PROPERTY);
            
			/*
			 * Se nao existir uma propriedade de autorizacao bloqueia acesso ao
			 * servico.
			 */
            if (lstToken == null || lstToken.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Usuário não autenticado.").build());
                return;
            }
            
            Jws<Claims> clm;
            String token;
            Gson gson;
            AuthVO auth;
            
            try {
            	clm = Jwts.parser().setSigningKey(JdConstantes.SECRET_CODE).parseClaimsJws(lstToken.get(0));
            	token = clm.getBody().getSubject();
            	gson = new Gson();
            	auth = gson.fromJson(token, AuthVO.class);
            	
            	if(new Timestamp(new Date().getTime()).after(auth.getExpires())){
            		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
            				.entity("Usuário expirado.").build());
            		return;
            	}
            	
            	/*
            	 * Verifica se o servico tem regras de acesso definidas.
            	 */
            	if (method.isAnnotationPresent(RolesAllowed.class)) {
            		
            		RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            		Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
            		
            		/*
            		 * Verifica se as informacoes de usuarios sao validas.
            		 */
            		if(!isUserAllowed(auth, rolesSet)){
            			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
            					.entity("Acesso a este serviço bloqueado para este usuário.").build());
            			return;
            		}
            	}
            	
            } catch (SignatureException se) {
            	se.printStackTrace();
            }
            
        }
    }

    /**
     * Verifica se as informacoes do usuario sao validas.
     *
     * @param auth
     * @param rolesSet
     * @return
     */
    private boolean isUserAllowed(final AuthVO auth, final Set<String> rolesSet) {

        boolean isAllowed = false;

        try {
//            if (auth != null) {
//                if (rolesSet.contains(auth.getGroup())) {
//                    isAllowed = true;
//                }
//            }
        } catch (Exception e) {
//            JBFLog.logError(AuthenticationFilter.class, e.getMessage(), e);
            isAllowed = false;
        }

        return isAllowed;

    }

}
