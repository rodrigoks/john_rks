package br.com.johndeere.controles;

import java.util.Collection;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.johndeere.exceptions.MoviesException;
import br.com.johndeere.exceptions.PeoplesException;
import br.com.johndeere.exceptions.SpeciesException;
import br.com.johndeere.servicos.ConsultMovie;
import br.com.johndeere.servicos.ConsultPeoples;
import br.com.johndeere.servicos.ConsultSpecies;
import br.com.johndeere.servicos.RetrieveSpeciesFromMovie;
import br.com.johndeere.vos.MovieVO;
import br.com.johndeere.vos.PeopleVO;
import br.com.johndeere.vos.ResponseVO;
import br.com.johndeere.vos.SpecieVO;

@Path("/johndeere")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JdController extends HttpServlet {
	
    @GET
//    @PermitAll
    public Object getArquivos(
    		@QueryParam("film_id") String film_id, 
    		@QueryParam("character_id") String character_id) throws Exception {

        ConsultMovie consultMovie;
        ConsultPeoples consultPeoples;
        ConsultSpecies consultSpecies;
        RetrieveSpeciesFromMovie result; 
        
        MovieVO movie = null;
        PeopleVO people = null;
        SpecieVO specie = null;
        ResponseVO response = null;
        Collection<PeopleVO> colPeople = null;

        try {
        	
        	if(film_id == null || film_id.equals(""))
        		throw new MoviesException("Parâmetro identificador de filme não encontrado.");
        	
        	if(character_id == null || character_id.equals(""))
        		throw new MoviesException("Parâmetro identificador de personagem não encontrado.");
        	
        	consultMovie = new ConsultMovie();
        	movie = consultMovie.consultMovies(film_id);
        	
        	consultPeoples = new ConsultPeoples();
        	people = consultPeoples.consultPeoples(movie, character_id);
        	
        	consultSpecies = new ConsultSpecies();
        	specie = consultSpecies.consultSpecies(people);
        	
        	result = new RetrieveSpeciesFromMovie();
        	colPeople = result.retrievePeopleBySpecie(movie, people);
        	
        	response = new ResponseVO(movie, people, specie, colPeople);
        	
        } catch (SpeciesException ee) {
			return Response.status(Status.BAD_REQUEST).entity(ee.getMessage()).build();
        } catch (MoviesException fe) {
			return Response.status(Status.BAD_REQUEST).entity(fe.getMessage()).build();
        } catch (PeoplesException pe) {
			return Response.status(Status.BAD_REQUEST).entity(pe.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.CONFLICT)
            		.entity("Erro gerado durante a execução deste serviço. Favor comunicar o Administrador.").build();
        }

        return Response.ok(response, MediaType.APPLICATION_JSON).build();

    }

}
