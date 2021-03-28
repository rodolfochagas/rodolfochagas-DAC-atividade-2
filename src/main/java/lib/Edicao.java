package lib;

import javax.json.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@XmlRootElement
public class Edicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numero;
    private String ano;
    private Date dataInicial;
    private Date dataFinal;
    private String cidadeSede;
    private String pais;
    @ManyToOne
    private Evento evento;

    public Edicao() {
    }

    public Edicao(String ent) {

    }

    public Edicao(String numero, String ano, Date dataInicial, Date dataFinal, String cidadeSede, String pais, Evento evento) {
        this.numero = numero;
        this.ano = ano;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.cidadeSede = cidadeSede;
        this.pais = pais;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getCidadeSede() {
        return cidadeSede;
    }

    public void setCidadeSede(String cidadeSede) {
        this.cidadeSede = cidadeSede;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public JsonObject toJson() {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("id", id)
                .add("numero", numero)
                .add("ano", ano)
                .add("dataInicial", dataInicial.toString() )
                .add("dataFinal", dataFinal.toString())
                .add("cidadeSede", cidadeSede)
                .add("pais", pais)
                .add("evento", evento.toJson())
                .build();
        return obj;
    }
}
