package consultaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.TurnoDTO;
import logic.ctrlTurnos;

@WebServlet("/verMiAgenda")
public class verMiAgenda extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) {
            response.sendRedirect("index.html");
            return;
        }
        
        String fechaStr = request.getParameter("fecha");
        
        if (fechaStr != null && !fechaStr.trim().isEmpty()) {
            LocalDate fecha = LocalDate.parse(fechaStr);
            ctrlTurnos ctrl = new ctrlTurnos();
            LinkedList<TurnoDTO> agenda = ctrl.getAgendaDiaria(u.getIdUsuario(), fecha);
            
            request.setAttribute("agenda", agenda);
            request.setAttribute("fechaSeleccionada", fechaStr);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/miAgenda.jsp");
        dispatcher.forward(request, response);
    }
}
