package lib;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named(value = "listaEventoBean")
@RequestScoped

public class ListaEventoBean {

    List<Evento> eventos;
    private EventoDAO dao;
    private Evento current;

    /**
     * Creates a new instance of ListaEventoBean
     */
    public ListaEventoBean() {
        dao = new EventoDAO();
    }

    public List<Evento> getEventos() {
        eventos = dao.buscaTodos();
        return eventos;
    }

    public String editaEvento(){

        return "edita-evento.xhtml";
    }



}
