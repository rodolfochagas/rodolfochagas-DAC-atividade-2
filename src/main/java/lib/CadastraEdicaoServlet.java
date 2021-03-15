package lib;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CadastraEdicaoServlet", value = "/CadastraEdicaoServlet")
public class CadastraEdicaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Edicao edicao = new Edicao();
        edicao.setNumero(request.getParameter("numero"));
        edicao.setAno(request.getParameter("ano"));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial = null;
        Date dataFinal = null;
        try {
            dataInicial = formato.parse(request.getParameter("dataInicial"));
            dataFinal = formato.parse(request.getParameter("dataFinal"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edicao.setDataInicial(dataInicial);
        edicao.setDataFinal(dataFinal);

        edicao.setCidadeSede(request.getParameter("cidadeSede"));
        edicao.setPais(request.getParameter("pais"));

        long idEvento = Long.parseLong(request.getParameter("idEvento"));

        try{
            EventoDAO eDao = new EventoDAO();
            Evento evento = eDao.recupera(idEvento);
            edicao.setEvento(evento);

            EdicaoDAO dao = new EdicaoDAO();
            dao.salva(edicao);
            Long id = edicao.getId();
            request.setAttribute("id", id);
            ServletContext servcontext = request.getServletContext();
            if(dao.recupera(id)!=null) {
                RequestDispatcher dispatcher =
                        servcontext.getRequestDispatcher("/MostraCadastroEdicao.jsp");
                dispatcher.include(request, response);
            }
            else {
                RequestDispatcher dispatcher =
                        servcontext.getRequestDispatcher("/MostraFalha.jsp");
                dispatcher.include(request, response);
            }

        } catch (Error error) {
            System.out.println(error);
        }

    }
}
