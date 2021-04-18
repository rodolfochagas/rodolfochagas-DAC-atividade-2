package lib;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;

@Named(value = "eventoBean")
@RequestScoped
public class EventoBean {

    private String nome;
    private String sigla;
    private String areaDeConcentracao;
    private String instituicaoOrganizadora;
    private boolean saved;
    private EventoDAO dao;

//    private List<Integer> index;
//    private Map<String, String> availableItems;

    public EventoBean() {
        nome = "";
        sigla = "";
        areaDeConcentracao = "";
        instituicaoOrganizadora = "";
        saved = false;
        dao = new EventoDAO();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }


    public String getAreaDeConcentracao() {
        return areaDeConcentracao;
    }

    public void setAreaDeConcentracao(String areaDeConcentracao) {
        this.areaDeConcentracao = areaDeConcentracao;
    }

    public String getInstituicaoOrganizadora() {
        return instituicaoOrganizadora;
    }

    public void setInstituicaoOrganizadora(String instituicaoOrganizadora) {
        this.instituicaoOrganizadora = instituicaoOrganizadora;
    }

    public String saveEvento() {
        Evento e = new Evento();
        e.setNome(nome);
        e.setSigla(sigla);
        e.setAreaDeConcentracao(areaDeConcentracao);
        e.setInstituicaoOrganizadora(instituicaoOrganizadora);

        dao.salva(e);

        return ("index.xhtml");
    }




}
