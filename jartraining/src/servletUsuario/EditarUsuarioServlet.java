package servletUsuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import entities.Usuario;
import logic.ctrlUsuario;

@WebServlet("/editarUsuario")
public class EditarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditarUsuarioServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("id"));
        ctrlUsuario ctrl = new ctrlUsuario();
        Usuario usuario = ctrl.getById(idUsuario);

        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarUsuario.jsp");
        dispatcher.forward(request, response);
    }
}