package consultaUsuario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;

@WebServlet("/gestionarDisponibilidad")
public class gestionarDisponibilidad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) {
            response.sendRedirect("index.html");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionarDisponibilidad.jsp");
        dispatcher.forward(request, response);
    }
}
