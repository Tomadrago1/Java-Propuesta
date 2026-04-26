package consultaUsuario;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Consulta;
import entities.Usuario;
import logic.ctrlTurnos;

@WebServlet("/registrarConsulta")
public class registrarConsulta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) {
            response.sendRedirect("index.html");
            return;
        }

        try {
            int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
            String fechaConsultaStr = request.getParameter("fecha_consulta");
            LocalDateTime fechaConsulta = LocalDateTime.parse(fechaConsultaStr);

            ctrlTurnos ctrl = new ctrlTurnos();
            Consulta consulta = ctrl.getConsulta(id_cliente, u.getIdUsuario(), fechaConsulta);

            if (consulta != null) {
                request.setAttribute("consulta", consulta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detalleConsulta.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("verMiAgenda");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("verMiAgenda");
        }
    }
}
