package servletUsuario;

import java.io.IOException;


import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.ctrlUsuario;

@WebServlet("/actualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String nombreUsuario = request.getParameter("nombreUsuario");
        int tipo_usuario = Integer.parseInt(request.getParameter("tipoUsuario"));
        ctrlUsuario ctrl = new ctrlUsuario();
        Boolean success = ctrl.modificar(id, nombre, apellido, email, nombreUsuario, tipo_usuario);

        if (success) {
            LinkedList<Usuario> usuarios = ctrl.getAll();
            request.setAttribute("listaUsuarios", usuarios);
            request.getRequestDispatcher("WEB-INF/UserManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }
}
