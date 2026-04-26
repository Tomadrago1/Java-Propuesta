package servletUsuario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.ctrlProfesion;
import java.util.LinkedList;
import entities.Profesion;

/**
 * Servlet implementation class crearUsuario
 */
@WebServlet("/crearUsuario")
public class crearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ctrlProfesion ctrlP = new ctrlProfesion();
		LinkedList<Profesion> profesiones = ctrlP.getAll();
		request.setAttribute("listaProfesiones", profesiones);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Usuario/crearUsuario.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ctrlProfesion ctrlP = new ctrlProfesion();
		LinkedList<Profesion> profesiones = ctrlP.getAll();
		request.setAttribute("listaProfesiones", profesiones);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Usuario/crearUsuario.jsp");
        dispatcher.forward(request, response);
	}

}