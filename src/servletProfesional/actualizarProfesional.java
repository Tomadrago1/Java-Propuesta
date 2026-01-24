package servletProfesional;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.ctrlUsuario;

/**
 * Servlet implementation class actualizarProfesional
 */
@WebServlet("/actualizarProfesional")
public class actualizarProfesional extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String profesion = request.getParameter("profesion");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        ctrlUsuario ctrl = new ctrlUsuario();
        Boolean success = ctrl.modificarProfesional(id, nombre, apellido, profesion, nombreUsuario, password, email);

        if (success) {
            LinkedList<Usuario> usuarios = ctrl.getAll();
            request.setAttribute("listaUsuarios", usuarios);
            request.getRequestDispatcher("WEB-INF/Usuario/UserManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }

}
