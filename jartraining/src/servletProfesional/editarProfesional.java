package servletProfesional;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.Profesional;
import logic.ctrlProfesional;

/**
 * Servlet implementation class editarProfesional
 */
@WebServlet("/editarProfesional")
public class editarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarProfesional() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idProfesional = Integer.parseInt(request.getParameter("id"));
        ctrlProfesional ctrl= new ctrlProfesional();
        Profesional profesional = ctrl.getById(idProfesional);
        request.setAttribute("profesional", profesional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarProfesional.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
	}

}
