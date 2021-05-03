package services;

import java.io.StringReader;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import lib.Edicao;
import lib.EdicaoDAO;
import lib.Evento;
import lib.EventoDAO;

@Path("edicao")
public class EdicaoService {
    private final JsonBuilderFactory factory;

    @Context
    UriInfo uriInfo;

    public EdicaoService() {
        factory = Json.createBuilderFactory(null);
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createJson(String ent) {
        EdicaoDAO dao = new EdicaoDAO();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();

        String numero = json.getString("numero");
        String ano = json.getString("ano");
        Date dataInicial = stringToDate(json.getString("dataInicial"));
        Date dataFinal = stringToDate(json.getString("dataFinal"));

        String cidadeSede = json.getString("cidadeSede");
        String pais = json.getString("pais");

        Evento evento = getEvento(json.getJsonNumber("idEvento").toString());

        Edicao e = new Edicao(numero,ano,dataInicial, dataFinal, cidadeSede, pais, evento);

        dao.salva(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(e.getId())).build();
        Response resp = Response.created(uri).build();
        return resp;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Edicao find(@PathParam("id") Long id) {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.recupera(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        EdicaoDAO dao = new EdicaoDAO();
        Edicao e = dao.recupera(id);
        if (e == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return e.toJson();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, JsonObject edicaoJson) {

        EdicaoDAO dao = new EdicaoDAO();
        Edicao edicao = dao.recupera(id);

        if(edicaoJson.containsKey("numero")){
            edicao.setNumero(edicaoJson.getString("numero"));
        }

        if(edicaoJson.containsKey("ano")){
            edicao.setAno(edicaoJson.getString("ano"));
        }

        if(edicaoJson.containsKey("dataInicial")){
            edicao.setDataInicial(stringToDate(edicaoJson.getString("dataInicial")));
        }

        if(edicaoJson.containsKey("dataFinal")){
            edicao.setDataFinal(stringToDate(edicaoJson.getString("dataFinal")));
        }

        if(edicaoJson.containsKey("cidadeSede")){
            edicao.setCidadeSede(edicaoJson.getString("cidadeSede"));
        }

        if(edicaoJson.containsKey("pais")){
            edicao.setPais(edicaoJson.getString("pais"));
        }

        if(edicaoJson.containsKey("idEvento")){
            edicao.setEvento(getEvento(edicaoJson.getJsonNumber("idEvento").toString()));
        }

        dao.atualiza(edicao);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        EdicaoDAO dao = new EdicaoDAO();
        dao.deleta(id);
    }

    public Date stringToDate (String dataString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Date stringToDateHyphen (String dataString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date data = null;
        try {
            data = formato.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Evento getEvento(String id) {
        long idEvento = Long.parseLong(id);
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.recupera(idEvento);
    }

    @GET
    @Path("evento/{eventoId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Edicao> findByEventoId(@PathParam("eventoId") Long eventoId) {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.buscaPorEvento(eventoId);
    }

    @GET
    @Path("data/{data}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Edicao> findByDate(@PathParam("data") String data) {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.buscaPorData(stringToDateHyphen(data));
    }

    @GET
    @Path("cidade/{cidade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Edicao> findByCity(@PathParam("cidade") String cidade) {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.buscaPorCidade(cidade);
    }

    @GET
    @Path("data/{data}/cidade/{cidade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Edicao> findByDateAndCity(@PathParam("data") String data, @PathParam("cidade") String cidade) {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.buscaPorDataECidade(stringToDateHyphen(data), cidade);
    }

    @GET
    @Path("todas")
    @Produces({
           MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON})
    public List<Edicao> findAll() {
        EdicaoDAO dao = new EdicaoDAO();
        return dao.buscaTodas();
    }
}
