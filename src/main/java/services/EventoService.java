package services;

import java.io.StringReader;
import java.net.URI;
import java.util.List;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lib.Evento;
import lib.EventoDAO;

@Path("evento")
public class EventoService {
    private final JsonBuilderFactory factory;

    @Context
    UriInfo uriInfo;

    public EventoService() {
        factory = Json.createBuilderFactory(null);
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createJson(String ent) {
        EventoDAO dao = new EventoDAO();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        Evento e = new Evento(ent);
        dao.salva(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(e.getId())).build();
        Response resp = Response.created(uri).build();
        return resp;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Evento find(@PathParam("id") Long id) {
        EventoDAO dao = new EventoDAO();
        return dao.recupera(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        EventoDAO dao = new EventoDAO();
        Evento e = dao.recupera(id);
        return e.toJson();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, JsonObject eventoJson) {

        EventoDAO dao = new EventoDAO();
        Evento evento = dao.recupera(id);

        if(eventoJson.containsKey("nome")){
            evento.setNome(eventoJson.getString("nome"));
        }

        if(eventoJson.containsKey("sigla")){
            evento.setSigla(eventoJson.getString("sigla"));
        }

        if(eventoJson.containsKey("instituicao")){
            evento.setInstituicaoOrganizadora(eventoJson.getString("instituicao"));
        }

        if(eventoJson.containsKey("area")){
            evento.setAreaDeConcentracao(eventoJson.getString("area"));
        }
        dao.atualiza(evento);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        EventoDAO dao = new EventoDAO();
        dao.deleta(id);
    }
}
