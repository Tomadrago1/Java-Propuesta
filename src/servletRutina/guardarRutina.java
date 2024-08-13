package servletRutina;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DaoRutina;
import entities.Rutina;
import logic.ctrlRutina;

/**
 * Servlet implementation class guardarRutina
 */
@WebServlet("/guardarRutina")
public class guardarRutina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarRutina() {
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

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        // Crear una instancia del Rutina
        Rutina nuevoRutina = new Rutina();
        nuevoRutina.setNombre(nombre);
        nuevoRutina.setDescripcion(descripcion);

        // Instanciar el DAO y guardar el rutina
        DaoRutina de = new DaoRutina();
        de.addRutina(nuevoRutina);

        // Redirigir o enviar un mensaje de éxito
        ctrlRutina ctrl = new ctrlRutina();
    	LinkedList<Rutina> rutinas = ctrl.getAll();

		request.setAttribute("listaRutinas", rutinas);
		//request.setAttribute("rutina", nombreRutina);
    	request.getRequestDispatcher("WEB-INF/rutinaManagement.jsp").forward(request, response);
    }
}