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

import entities.Consulta;
import entities.Usuario;
import entities.Profesional;
import logic.ctrlTurnos;
import logic.ctrlUsuario;
import logic.EmailService;

@WebServlet("/confirmarTurno")
public class confirmarTurno extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
        if (cliente == null) {
            response.sendRedirect("index.html");
            return;
        }

        int id_profesional = Integer.parseInt(request.getParameter("id_profesional"));
        String fechaStr = request.getParameter("fecha");
        String horaStr = request.getParameter("hora"); // Formato HH:mm
        
        LocalDate fecha = LocalDate.parse(fechaStr);
        LocalTime hora = LocalTime.parse(horaStr);
        LocalDateTime fecha_consulta = LocalDateTime.of(fecha, hora);

        Consulta c = new Consulta();
        c.setId_cliente(cliente.getIdUsuario());
        c.setId_profesional(id_profesional);
        c.setFecha_consulta(fecha_consulta);
        c.setDesc_resultados("");
        c.setEstado("Pendiente");

        ctrlTurnos ctrl = new ctrlTurnos();
        boolean success = ctrl.agendarTurno(c);

        if (success) {
            // Enviar email de forma asíncrona (si el usuario tiene email cargado)
            if (cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty()) {
                ctrlUsuario ctrlU = new ctrlUsuario();
                Profesional prof = ctrlU.getProfesionalById(id_profesional);
                String nombreProf = prof != null ? prof.getNombre() + " " + prof.getApellido() : "el profesional";
                
                EmailService.enviarConfirmacionTurnoAsync(
                    cliente.getEmail(), 
                    cliente.getNombre(), 
                    nombreProf, 
                    fechaStr, 
                    horaStr
                );
            }

            request.getSession().setAttribute("mensajeExito", "¡Turno reservado con éxito para el " + fechaStr + " a las " + horaStr + "!");
            response.sendRedirect("signin");
        } else {
            response.getWriter().append("Ocurrió un error al confirmar el turno.");
        }
    }
}
