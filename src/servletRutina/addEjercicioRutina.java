package servletRutina;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.*;
import logic.ctrlEjercicios;
import logic.ctrlRutina;

/**
 * Servlet implementation class addEjercicioRutina
 */
@WebServlet("/addEjercicioRutina")
public class addEjercicioRutina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEjercicioRutina() {
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
		int idR = Integer.parseInt(request.getParameter("id_rut"));
		
		ctrlEjercicios ctrlE = new ctrlEjercicios();
		ctrlRutina ctrlR = new ctrlRutina();
		
		LinkedList<Ejercicio> ejercicios = ctrlE.getAll();
		request.setAttribute("listaEjercicios", ejercicios);
		
		Rutina r = ctrlR.getOne(idR);
		request.setAttribute("rutina", r);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ejercicioRutinaManagement.jsp");
        dispatcher.forward(request, response);
	}

}
