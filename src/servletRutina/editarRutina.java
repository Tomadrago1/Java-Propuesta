package servletRutina;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import entities.*;
import logic.ctrlRutina;

@WebServlet("/editarRutina")
public class editarRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public editarRutina() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRutina = Integer.parseInt(request.getParameter("id"));
        ctrlRutina ctrl = new ctrlRutina();
        Rutina rut = ctrl.getOne(idRutina);

        request.setAttribute("rutina", rut);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Rutina/editarRutina.jsp");
        dispatcher.forward(request, response);
    }
}