package br.com.johndeere.controles;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.johndeere.exceptions.MoviesException;
import br.com.johndeere.servicos.ConsultMovie;
import br.com.johndeere.servicos.ConsultPeoples;
import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;

@Path("/JohnDeere")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JdController extends HttpServlet {
	
    @GET
    @PermitAll
    public Object getArquivos(
    		@QueryParam("film_id") String film_id, 
    		@QueryParam("character_id") String character_id) throws Exception {

        MovieVO movie = null;
        PeopleVO people = null;
        ConsultMovie consultMovie;
        ConsultPeoples consultPeoples;

        try {
        	
        	consultMovie = new ConsultMovie();
        	movie = consultMovie.consultMovies(film_id);
        	consultPeoples = new ConsultPeoples();
        	people = consultPeoples.consultarFilmes(movie, character_id);
        	
        } catch (MoviesException fe) {
			// TODO: handle exception
        } catch (Exception e){
            return Response.status(Response.Status.CONFLICT)
            		.entity("Erro gerado durante a execução deste serviço. Favor comunicar o Administrador.").build();
        }

        return Response.ok(people, MediaType.APPLICATION_JSON).build();

    }

}
