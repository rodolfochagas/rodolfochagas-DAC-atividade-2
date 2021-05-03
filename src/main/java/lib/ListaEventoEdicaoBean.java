package lib;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named(value = "listaEventoEdicaoBean")
@RequestScoped
public class ListaEventoEdicaoBean {

    private EdicaoDAO dao;
    private List<EventoEdicaoBean> lista;
    private Date data;
    private String dataString;

    public ListaEventoEdicaoBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        data = stringToDateHyphen(params.get("j_idt13:j_idt15"));
        System.out.println("Data = " + data);
        dataString = "";

        dao = new EdicaoDAO();
        lista = new ArrayList<>();
    }

    public List<EventoEdicaoBean> getLista() {
        List<Edicao> edicoes;

        if(data == null) {
            edicoes = dao.buscaTodas();
        }
        else {
            edicoes = dao.buscaPorData(data);
        }

        List<Evento> eventos = new ArrayList<>();

        for(Edicao edicao : edicoes){
            Evento evento = edicao.getEvento();
            if( evento != null && !eventos.contains(evento)) {
                eventos.add(evento);
            }
        }

        for (Evento evento : eventos) {
            EventoEdicaoBean eventoEdicao = new EventoEdicaoBean();
            eventoEdicao.setEvento(evento);
            List <Edicao> edicoesEvento = new ArrayList<>();

            for (Edicao e : edicoes){
                if(e.getEvento().getId() == evento.getId()) {
                    edicoesEvento.add(e);
                }
            }

            eventoEdicao.setEdicoes(edicoesEvento);
            lista.add(eventoEdicao);
        }
        return lista;
    }

    public Date stringToDateHyphen (String dataString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date data = null;
        if(dataString != null){
            try {
                data = formato.parse(dataString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String setData() {

        return "mostra-evento-edicao.xhtml?data=" + dataString;
    }


}
