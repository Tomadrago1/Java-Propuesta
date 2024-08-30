package servletUsuario;

import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import entities.Profesional;
import logic.ctrlProfesional;
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
        int tipoUsuario = 0;
        ctrlUsuario ctrl = new ctrlUsuario();
        Usuario nuevoUsuario = new Usuario();
        switch (tipoUsuarioString) {
            case "administrador":
                tipoUsuario = 1;
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setNombreUsuario(nombreUsuario);
                nuevoUsuario.setTipoUsu(tipoUsuario);
                nuevoUsuario.setPassword(password);
                nuevoUsuario.setEstado(true);
                ctrl.add(nuevoUsuario);
                break;
            case "cliente":
                tipoUsuario = 3;
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setNombreUsuario(nombreUsuario);
                nuevoUsuario.setTipoUsu(tipoUsuario);
                nuevoUsuario.setPassword(password);
                nuevoUsuario.setEstado(true);
                ctrl.add(nuevoUsuario);
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
                ctrlProfesional ctrlP = new ctrlProfesional();
                ctrlP.add(nuevoProfesional);
                break;
            default:
                // Manejar caso de valor no esperado o nulo
                tipoUsuario = -1; // Puedes usar otro valor o manejarlo de acuerdo a tu lógica
                break;
        }

        LinkedList<Usuario> usuarios = ctrl.getAll();
        request.setAttribute("listaUsuarios", usuarios);
        request.setAttribute("ususario", nombreUsuario);
        request.getRequestDispatcher("WEB-INF/Usuario/UserManagement.jsp").forward(request, response);
    }
}