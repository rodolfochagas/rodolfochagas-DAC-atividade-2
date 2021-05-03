package lib;

import javax.json.*;
import javax.persistence.*;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String sigla;
    private String areaDeConcentracao;
    private String instituicaoOrganizadora;
    @OneToMany(mappedBy = "evento")
    private List<Edicao> edicoes;

    public Evento(){
    }

    public Evento(String ent) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();

        this.nome = json.getString("nome");
        this.sigla = json.getString("sigla");
        this.areaDeConcentracao = json.getString("area");
        this.instituicaoOrganizadora = json.getString("instituicao");
    }

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

    public JsonObject toJson() {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("id", id)
                .add("nome", nome)
                .add("sigla", sigla)
                .add("instituicao", instituicaoOrganizadora)
                .add("area", areaDeConcentracao)
                .build();
        return obj;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }
}
