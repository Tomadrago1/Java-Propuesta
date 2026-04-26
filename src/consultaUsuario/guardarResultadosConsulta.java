package consultaUsuario;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Consulta;
import entities.Usuario;
import logic.ctrlTurnos;

@WebServlet("/guardarResultadosConsulta")
public class guardarResultadosConsulta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            String descResultados = request.getParameter("desc_resultados");

            Consulta c = new Consulta();
            c.setId_cliente(id_cliente);
            c.setId_profesional(u.getIdUsuario());
            c.setFecha_consulta(fechaConsulta);
            c.setDesc_resultados(descResultados);

            ctrlTurnos ctrl = new ctrlTurnos();
            boolean ok = ctrl.updateResultadosConsulta(c);

            if (ok) {
                request.getSession().setAttribute("mensajeExito", "Consulta registrada exitosamente.");
            } else {
                request.getSession().setAttribute("errorAgenda", "Hubo un error al registrar la consulta.");
            }
            
            // Redirect back to miAgenda. We need to pass the date so it shows the same day.
            response.sendRedirect("verMiAgenda?fecha=" + fechaConsulta.toLocalDate().toString());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("verMiAgenda");
        }
    }
}
