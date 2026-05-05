package consultaUsuario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;

@WebServlet("/seleccionarProfesional")
public class seleccionarProfesional extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_profesional = Integer.parseInt(request.getParameter("id"));
        
        ctrlUsuario ctrl = new ctrlUsuario();
        Profesional profesional = ctrl.getProfesionalById(id_profesional);
        
        request.setAttribute("profesional", profesional);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/agendarTurno.jsp");
        dispatcher.forward(request, response);
    }
}
