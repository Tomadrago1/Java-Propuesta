package servletUsuario;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.ctrlUsuario;

@WebServlet("/actualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String nombreUsuario = request.getParameter("nombreUsuario");
        int tipo_usuario = Integer.parseInt(request.getParameter("tipoUsuario"));
        String password = request.getParameter("password");
        ctrlUsuario ctrl = new ctrlUsuario();
        Boolean success = ctrl.modificarUsuario(id, nombre, apellido, email, nombreUsuario, tipo_usuario, password);

        if (success) {
            LinkedList<Usuario> usuarios = ctrl.getAll();
            request.setAttribute("listaUsuarios", usuarios);
            request.getRequestDispatcher("WEB-INF/Usuario/UserManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }
}