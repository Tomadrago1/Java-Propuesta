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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        int tipoUsuario;
        try {
            tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario")); // Asegúrate de que este parámetro se puede convertir a int
        } catch (NumberFormatException e) {
            tipoUsuario = 0; // O maneja el error de otra manera
        }

        // Crear una instancia del usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setTipoUsu(tipoUsuario);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setEstado(true);// Asegúrate de que la clase Usuario tenga este método

        // Instanciar el DAO y guardar el usuario
        ctrlUsuario ctrl = new ctrlUsuario();
        ctrl.add(nuevoUsuario);
    	LinkedList<Usuario> usuarios = ctrl.getAll();
    	/*Usuario usuario = daoUsuario.getUserById(id);
    	request.getSession().setAttribute("usuario", usuario);*/
		request.setAttribute("listaUsuarios", usuarios);
		request.setAttribute("ususario", nombreUsuario);
    	request.getRequestDispatcher("WEB-INF/UserManagement.jsp").forward(request, response);
    }
}