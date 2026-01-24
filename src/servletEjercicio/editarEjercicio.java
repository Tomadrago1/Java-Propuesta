package servletEjercicio;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import entities.*;
import logic.ctrlEjercicios;

//Aca se hace el getOne del ejercicio que se quiere editar y se manda a editarEjercicio.jsp

@WebServlet("/editarEjercicio")
public class editarEjercicio extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public editarEjercicio() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEjercicio = Integer.parseInt(request.getParameter("id"));

        ctrlEjercicios ctrl = new ctrlEjercicios();
        Ejercicio ej = ctrl.getOne(idEjercicio);

        request.setAttribute("ejercicio", ej);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ejercicio/editarEjercicio.jsp");
        dispatcher.forward(request, response);
    }
}