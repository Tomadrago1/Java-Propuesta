package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Usuario;

@WebServlet("/HomeRedirect")
public class HomeRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Integer tipo = null;
        Object tipoObj = session.getAttribute("tipoUsuario");
        if (tipoObj instanceof Integer) {
            tipo = (Integer) tipoObj;
        } else if (tipoObj instanceof String) {
            try {
                tipo = Integer.valueOf((String) tipoObj);
            } catch (NumberFormatException e) {
                tipo = null;
            }
        }
        if (tipo == null && usuario != null) {
            tipo = usuario.getTipoUsu();
        }
        if (tipo == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        switch (tipo) {
            case 1:
                response.sendRedirect(request.getContextPath() + "/WEB-INF/vistaAdmin.jsp");
                break;
            case 2:
                response.sendRedirect(request.getContextPath() + "/WEB-INF/vistaProfesional.jsp");
                break;
            case 3:
                response.sendRedirect(request.getContextPath() + "/WEB-INF/vistaUsuario.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}