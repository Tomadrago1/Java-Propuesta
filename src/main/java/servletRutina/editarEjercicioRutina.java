package servletRutina;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/editarEjercicioRutina")
public class editarEjercicioRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public editarEjercicioRutina() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRutina = Integer.parseInt(request.getParameter("id_rut"));
        int idEjercicio = Integer.parseInt(request.getParameter("id_eje"));
        int series = Integer.parseInt(request.getParameter("series"));
        int repes = Integer.parseInt(request.getParameter("repes"));
        
        request.setAttribute("id_rut", idRutina);
        request.setAttribute("id_eje", idEjercicio);
        request.setAttribute("series", series);
        request.setAttribute("repes", repes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarEjercicioRutina.jsp");
        dispatcher.forward(request, response);
    }
}