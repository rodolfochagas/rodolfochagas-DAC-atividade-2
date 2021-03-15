package lib;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String sigla;
    private String areaDeConcentracao;
    private String instituicaoOrganizadora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
