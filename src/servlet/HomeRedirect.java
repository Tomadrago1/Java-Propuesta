package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entities.Usuario;

@WebServlet("/HomeRedirect")
public class HomeRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");
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
            response.sendRedirect(request.getContextPath() + "/index.html");
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
                response.sendRedirect(request.getContextPath() + "/index.html");
        }
    }
}