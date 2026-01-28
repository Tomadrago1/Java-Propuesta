package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.ctrlUsuario;

/**
 * Servlet implementation class signin
 */
@WebServlet({ "/signin", "/Signin", "/SignIn", "/signIn", "/SIGNIN" })
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       // Redirigir al home correspondiente si el usuario está autenticado
		       Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
		       if (usu == null) {
			       // Si no está autenticado, ir al login
			       response.sendRedirect("login.jsp");
			       return;
		       }
		       switch (usu.getTipoUsu()) {
			       case 1:
				       request.getRequestDispatcher("WEB-INF/vistaAdmin.jsp").forward(request, response);
				       break;
			       case 2:
				       request.getRequestDispatcher("WEB-INF/vistaProfesional.jsp").forward(request, response);
				       break;
			       case 3:
				       request.getRequestDispatcher("WEB-INF/vistaUsuario.jsp").forward(request, response);
				       break;
			       default:
				       response.getWriter().append("Tipo de usuario no válido.");
				       break;
		       }
	       }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = new Usuario();
		ctrlUsuario ctrl = new ctrlUsuario();

		String user = request.getParameter("usuario");
		String password = request.getParameter("password");
		usu.setNombreUsuario(user);
		usu.setPassword(password);
		usu = ctrl.validate(usu);
		if (usu == null || usu.getEstado() == false) {
			request.setAttribute("errorMessage", "El usuario no existe o las credenciales son incorrectas.");
			request.getRequestDispatcher("WEB-INF/errorLogin.jsp").forward(request, response);
		} else {
			switch (usu.getTipoUsu()) {
				case 1:
					request.getSession().setAttribute("usuario", usu);
					request.getRequestDispatcher("WEB-INF/vistaAdmin.jsp").forward(request, response);
					break;
				case 2:
					request.getSession().setAttribute("usuario", usu);
					request.getRequestDispatcher("WEB-INF/vistaProfesional.jsp").forward(request, response);
					break;
				case 3:
					request.getSession().setAttribute("usuario", usu);
					request.getRequestDispatcher("WEB-INF/vistaUsuario.jsp").forward(request, response);
					break;
				default:
					response.getWriter().append("Tipo de usuario no válido.");
					break;
			}
		}

	}

}