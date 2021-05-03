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

@Named(value = "buscaBean")
@RequestScoped
public class BuscaBean {

    private String dataString;

    public BuscaBean() {
        dataString = "dd-mm-aaaa";
    }

    public String buscar() {
        dataString = dataString;
        return "mostra-evento-edicao.xhtml?data=" + dataString;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}
