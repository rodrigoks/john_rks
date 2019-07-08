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

import br.com.johndeere.exceptions.FilmsException;
import br.com.johndeere.servicos.ConsultFilm;
import br.com.johndeere.vos.FilmVO;

@Path("/filmes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilmsController extends HttpServlet {
	
    @GET
    @PermitAll
    public Object getArquivos(
    		@QueryParam("film_id") String film_id, 
    		@QueryParam("character_id") String character_id) throws Exception {

        FilmVO obj = null;
        ConsultFilm filmes;

        try {
        	
        	filmes = new ConsultFilm();
        	obj = filmes.consultarFilmes(film_id);
        	
        } catch (FilmsException fe) {
			// TODO: handle exception
        } catch (Exception e){
            return Response.status(Response.Status.CONFLICT)
            		.entity("Erro gerado durante a execução deste serviço. Favor comunicar o Administrador.").build();
        }

        return Response.ok(obj, MediaType.APPLICATION_JSON).build();

    }

}