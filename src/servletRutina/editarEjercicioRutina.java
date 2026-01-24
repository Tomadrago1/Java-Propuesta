package servletRutina;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

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
        String tiempo = request.getParameter("tiempo");
        
        request.setAttribute("id_rut", idRutina);
        request.setAttribute("id_eje", idEjercicio);
        request.setAttribute("series", series);
        request.setAttribute("repes", repes);
        request.setAttribute("tiempo", tiempo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarEjercicioRutina.jsp");
        dispatcher.forward(request, response);
    }
}