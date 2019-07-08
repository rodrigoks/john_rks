package br.com.johndeere.servlet.filters;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Created by jbusiness on 30/03/17.
 */
@Singleton
@Provider
public class CORSFilter implements ContainerResponseFilter {

    private final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";
    private final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";
    private final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private final String ACCESS_CONTROL_ALLOW_METHODS_VALUES = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
    private final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private final String ACCESS_CONTROL_ALLOW_HEADERS_VALUES = "X-Requested-With, Content-Type, X-Codingpedia, Origin, Authorization, Token, enctype";
    private final String ACCESS_CONTROL_ALLOW_MAX_AGE = "Access-Control-Max-Age";
    private final String ACCESS_CONTROL_ALLOW_MAX_AGE_VALUE = "86400";
    private final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    private final String ACCESS_CONTROL_EXPOSE_HEADERS_VALUE = "content-disposition";

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN))
            response.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_CREDENTIALS))
            response.getHeaders().add(ACCESS_CONTROL_ALLOW_CREDENTIALS, ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_METHODS))
            response.getHeaders().add(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_VALUES);

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_HEADERS))
            response.getHeaders().add(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_VALUES);

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_MAX_AGE))
            response.getHeaders().add(ACCESS_CONTROL_ALLOW_MAX_AGE, ACCESS_CONTROL_ALLOW_MAX_AGE_VALUE);

        if(!response.getHeaders().containsKey(ACCESS_CONTROL_EXPOSE_HEADERS))
            response.getHeaders().add(ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS_VALUE);
        
    }

}
