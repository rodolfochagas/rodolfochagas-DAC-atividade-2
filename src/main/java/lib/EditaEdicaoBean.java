package lib;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named(value = "editaEdicaoBean")
@ViewScoped
public class EditaEdicaoBean {

    private Edicao edicao;
    private String id;
    private String ano;
    private String cidadeSede;
    private Date dataInicial;
    private Date dataFinal;
    private String numero;
    private String pais;
    private boolean saved;
    private EdicaoDAO dao;
    private EventoDAO eventoDAO;
    private Evento evento;


    public EditaEdicaoBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        System.out.println("Id = " + id);
        dao = new EdicaoDAO();
        eventoDAO = new EventoDAO();
        edicao = dao.recupera(Long.parseLong(id));
        evento = eventoDAO.recupera(edicao.getEvento().getId());
        ano = edicao.getAno();
        cidadeSede = edicao.getCidadeSede();
        dataInicial = edicao.getDataInicial();
        dataFinal = edicao.getDataFinal();
        numero = edicao.getNumero();
        pais = edicao.getPais();
    }

    public String setEdicao() {
        edicao.setEvento(evento);
        edicao.setAno(ano);
        edicao.setCidadeSede(cidadeSede);
        edicao.setDataInicial(dataInicial);
        edicao.setDataFinal(dataFinal);
        edicao.setNumero(numero);
        edicao.setPais(pais);
        dao.atualiza(edicao);
        return "mostra-eventos";
    }

    public String removeEdicao() {
        dao.deleta(edicao.getId());
        return "mostra-eventos";
    }



//    public List<Edicao> getEdicoes() {
//        edicoes = edicaoDAO.buscaPorEvento(e.getId());
//        return edicoes;
//    }

    public String getId() { return id; }

    public Edicao getEdicao() {
        return edicao;
    }

    public String getAno() {
        return ano;
    }

    public String getCidadeSede() {
        return cidadeSede;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public String getNumero() {
        return numero;
    }

    public String getPais() {
        return pais;
    }

    public boolean isSaved() {
        return saved;
    }

    public EdicaoDAO getDao() {
        return dao;
    }

    public EventoDAO getEventoDAO() {
        return eventoDAO;
    }

    public Evento getEvento() {
        return evento;
    }
}
