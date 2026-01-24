package servletUsuario;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import entities.Profesional;
import logic.ctrlUsuario;

/**
 * Servlet implementation class GuardarUsuario
 */
@WebServlet("/GuardarUsuario")
public class GuardarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarUsuario() {
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
        // Recoger los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String tipoUsuarioString = request.getParameter("tipoUsuario");
        String profesion = request.getParameter("profesion");
        ctrlUsuario ctrl = new ctrlUsuario();
        Usuario nuevoUsuario;
        int tipoUsuario;
        switch (tipoUsuarioString) {
            case "administrador":
                nuevoUsuario = new Usuario();
                tipoUsuario = 1;
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setNombreUsuario(nombreUsuario);
                nuevoUsuario.setTipoUsu(tipoUsuario);
                nuevoUsuario.setPassword(password);
                nuevoUsuario.setEstado(true);
                ctrl.addUsuario(nuevoUsuario);
                break;
            case "cliente":
                nuevoUsuario = new Usuario();
                tipoUsuario = 3;
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setNombreUsuario(nombreUsuario);
                nuevoUsuario.setTipoUsu(tipoUsuario);
                nuevoUsuario.setPassword(password);
                nuevoUsuario.setEstado(true);
                ctrl.addUsuario(nuevoUsuario);
                break;
            case "profesional":
                tipoUsuario = 2;
                Profesional nuevoProfesional = new Profesional();
                nuevoProfesional.setNombre(nombre);
                nuevoProfesional.setApellido(apellido);
                nuevoProfesional.setEmail(email);
                nuevoProfesional.setNombreUsuario(nombreUsuario);
                nuevoProfesional.setTipoUsu(tipoUsuario);
                nuevoProfesional.setPassword(password);
                nuevoProfesional.setEstado(true);
                nuevoProfesional.setProfesion(profesion);
                ctrl.addProfesional(nuevoProfesional);
                break;
            default:
                break;
        }

        LinkedList<Usuario> usuarios = ctrl.getAll();
        request.setAttribute("listaUsuarios", usuarios);
        request.setAttribute("ususario", nombreUsuario);
        request.getRequestDispatcher("WEB-INF/Usuario/UserManagement.jsp").forward(request, response);
    }
}