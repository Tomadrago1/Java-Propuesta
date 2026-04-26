package consultaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Horario;
import entities.Usuario;
import logic.ctrlTurnos;

@WebServlet("/guardarDisponibilidad")
public class guardarDisponibilidad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) {
            response.sendRedirect("index.html");
            return;
        }

        String fechaStr = request.getParameter("fecha");
        String horaDesdeStr = request.getParameter("horaDesde");
        String horaHastaStr = request.getParameter("horaHasta");

        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            LocalTime horaDesde = LocalTime.parse(horaDesdeStr);
            LocalTime horaHasta = LocalTime.parse(horaHastaStr);

            if (horaHasta.isBefore(horaDesde) || horaHasta.equals(horaDesde)) {
                request.getSession().setAttribute("errorDisponibilidad", "La hora de fin debe ser mayor a la hora de inicio.");
                response.sendRedirect("gestionarDisponibilidad");
                return;
            }

            Horario h = new Horario();
            h.setId_profesional(u.getIdUsuario());
            h.setFecha_hora_desde(LocalDateTime.of(fecha, horaDesde));
            h.setFecha_hora_hasta(LocalDateTime.of(fecha, horaHasta));

            ctrlTurnos ctrl = new ctrlTurnos();
            boolean success = ctrl.agregarDisponibilidad(h);

            if (success) {
                request.getSession().setAttribute("mensajeExito", "Disponibilidad guardada con éxito.");
            } else {
                request.getSession().setAttribute("errorDisponibilidad", "Ocurrió un error al guardar.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("errorDisponibilidad", "Formato de fecha u hora inválido.");
        }

        response.sendRedirect("gestionarDisponibilidad");
    }
}
