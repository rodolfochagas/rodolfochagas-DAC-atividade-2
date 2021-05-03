package lib;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Named(value = "edicaoBean")
@RequestScoped
public class EdicaoBean {

    private String ano;
    private String cidadeSede;
    private String dataInicialString;
    private String dataFinalString;
    private String numero;
    private String pais;
    private boolean saved;
    private EdicaoDAO dao;
    private EventoDAO eventoDAO;
    private String idEvento;


//    private List<Integer> index;
//    private Map<String, String> availableItems;

    public EdicaoBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        idEvento = params.get("id");
        System.out.println("Id = " + idEvento);

        ano = "";
        cidadeSede = "";
        dataInicialString = "dd/mm/aaaa";
        dataFinalString = "dd/mm/aaaa";
        numero = "";
        pais = "";
        saved = false;
        dao = new EdicaoDAO();
        eventoDAO = new EventoDAO();
    }

    public String saveEdicao() {
        Evento evento = eventoDAO.recupera(Long.parseLong(idEvento));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial = null;
        Date dataFinal = null;
        try {
            dataInicial = formato.parse(dataInicialString);
            dataFinal = formato.parse(dataFinalString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Edicao e = new Edicao();
        e.setAno(ano);
        e.setCidadeSede(cidadeSede);
        e.setDataInicial(dataInicial);
        e.setDataFinal(dataFinal);
        e.setNumero(numero);
        e.setPais(pais);
        e.setEvento(evento);
        dao.salva(e);
        return ("index.xhtml");
    }

    public String getAno() {
        return ano;
    }

    public String getCidadeSede() {
        return cidadeSede;
    }

    public String getDataInicialString() { return dataInicialString; }

    public String getDataFinalString() {
        return dataFinalString;
    }

    public String getNumero() {
        return numero;
    }

    public String getPais() {
        return pais;
    }

    public String getIdEvento() { return idEvento; }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setCidadeSede(String cidadeSede) {
        this.cidadeSede = cidadeSede;
    }

    public void setDataInicialString(String dataInicialString) {
        this.dataInicialString = dataInicialString;
    }

    public void setDataFinalString(String dataFinalString) {
        this.dataFinalString = dataFinalString;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }
}
