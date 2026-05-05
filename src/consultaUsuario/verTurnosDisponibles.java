package consultaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlTurnos;
import logic.ctrlUsuario;

@WebServlet("/verTurnosDisponibles")
public class verTurnosDisponibles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_profesional = Integer.parseInt(request.getParameter("id_profesional"));
        String fechaStr = request.getParameter("fecha");
        
        ctrlUsuario ctrlU = new ctrlUsuario();
        Profesional profesional = ctrlU.getProfesionalById(id_profesional);
        request.setAttribute("profesional", profesional);
        request.setAttribute("fechaSeleccionada", fechaStr);

        if (fechaStr != null && !fechaStr.trim().isEmpty()) {
            LocalDate fecha = LocalDate.parse(fechaStr);
            ctrlTurnos ctrlT = new ctrlTurnos();
            LinkedList<LocalTime> turnos = ctrlT.getTurnosDisponibles(id_profesional, fecha);
            request.setAttribute("turnosDisponibles", turnos);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/agendarTurno.jsp");
        dispatcher.forward(request, response);
    }
}
