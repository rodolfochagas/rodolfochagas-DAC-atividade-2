package lib;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastraEventoServlet", value = "/CadastraEventoServlet")
public class CadastraEventoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Evento evento = new Evento();
        evento.setNome(request.getParameter("nome"));
        evento.setSigla(request.getParameter("sigla"));
        evento.setAreaDeConcentracao(request.getParameter("area"));
        evento.setInstituicaoOrganizadora(request.getParameter("instituicao"));

        try{
            EventoDAO dao = new EventoDAO();
            dao.salva(evento);
            Long id = evento.getId();
            request.setAttribute("id", id);
            ServletContext servcontext = request.getServletContext();
            if(dao.recupera(id)!=null) {
                RequestDispatcher dispatcher =
                        servcontext.getRequestDispatcher("/MostraCadastroEvento.jsp");
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
