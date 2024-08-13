package servletRutina;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import entities.*;
import logic.ctrlRutina;

@WebServlet("/editarRutina")
public class editarRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public editarRutina() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRutina = Integer.parseInt(request.getParameter("id"));
        ctrlRutina ctrl = new ctrlRutina();
        Rutina rut = ctrl.getOne(idRutina);

        request.setAttribute("rutina", rut);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarRutina.jsp");
        dispatcher.forward(request, response);
    }
}