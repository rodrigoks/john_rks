package br.com.johndeere.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by jbusiness on 30/03/17.
 */
@ApplicationPath("/api/jdtest")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
        packages("br.com.johndeere");
        register(MultiPartFeature.class);
    }
}
