package profesional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Necesidad;
import entities.Nutriente;
import entities.Usuario;
import logic.ctrlNecesidad;
import logic.ctrlNutriente;
import logic.ctrlUsuario;

@WebServlet("/prepararAsignacion")
public class prepararAsignacion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) { // 2 = Profesional
            response.sendRedirect("index.html");
            return;
        }

        try {
            int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
            
            ctrlUsuario cu = new ctrlUsuario();
            Usuario paciente = cu.getUserById(idPaciente);

            ctrlNutriente cn = new ctrlNutriente();
            LinkedList<Nutriente> nutrientes = cn.getAll();

            ctrlNecesidad cnec = new ctrlNecesidad();
            LinkedList<Necesidad> necesidadesHoy = cnec.getUltimasNecesidadesPorUsuario(idPaciente);

            request.setAttribute("paciente", paciente);
            request.setAttribute("nutrientes", nutrientes);
            request.setAttribute("necesidadesHoy", necesidadesHoy);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asignarNecesidades.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signin");
        }
    }
}
