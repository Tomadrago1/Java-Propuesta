package servletUsuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import entities.Usuario;
import entities.Profesional;
import logic.ctrlUsuario;

@WebServlet("/editarUsuario")
public class EditarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditarUsuarioServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("id"));
        int tipoUsuario = Integer.parseInt(request.getParameter("tipo_usu"));
        ctrlUsuario ctrl = new ctrlUsuario();
        if (tipoUsuario == 2) {
            Profesional profesional = ctrl.getProfesionalById(idUsuario);
            request.setAttribute("profesional", profesional);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Profesional/editarProfesional.jsp");
            dispatcher.forward(request, response);
        } else {
            Usuario usuario = ctrl.getUserById(idUsuario);
            request.setAttribute("usuario", usuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Usuario/editarUsuario.jsp");
            dispatcher.forward(request, response);
        }
    }
}