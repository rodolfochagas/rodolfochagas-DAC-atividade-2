package lib;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "eventoEdicaoBean")
@RequestScoped
public class EventoEdicaoBean {

    private Evento evento;
    private List<Edicao> edicoes;

    public EventoEdicaoBean() {
    }

    public Evento getEvento() {
        return evento;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setEdicoes(List<Edicao> edicoes) {
        this.edicoes = edicoes;
    }
}
