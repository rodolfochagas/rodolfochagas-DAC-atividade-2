package lib;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Named;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "editaEventoBean")
@ViewScoped
public class EditaEventoBean {

    private Evento e;
    private String id;
    private EventoDAO dao;
    private String nome;
    private String sigla;
    private String areaDeConcentracao;
    private String instituicaoOrganizadora;
    private boolean saved;

    public EditaEventoBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = params.get("id");
        System.out.println("Id = " + id);
        dao = new EventoDAO();
        e = dao.recupera(Long.parseLong(id));
        nome = e.getNome();
        sigla = e.getSigla();
        areaDeConcentracao = e.getAreaDeConcentracao();
        instituicaoOrganizadora = e.getInstituicaoOrganizadora();
    }

    public String getId() { return id; }

    public String setEvento() {
        e.setNome(nome);
        e.setSigla(sigla);
        e.setAreaDeConcentracao(areaDeConcentracao);
        e.setInstituicaoOrganizadora(instituicaoOrganizadora);
        dao.atualiza(e);
        return "mostra-eventos";
    }

    public String removeEvento() {
        dao.deleta(e.getId());
        return "mostra-eventos";
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

}
