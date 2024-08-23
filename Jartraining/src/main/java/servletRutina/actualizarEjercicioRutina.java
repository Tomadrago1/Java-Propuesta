package servletRutina;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Rutina;
import logic.ctrlRutina;

@WebServlet("/actualizarEjercicioRutina")
public class actualizarEjercicioRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_rut = Integer.parseInt(request.getParameter("id_rut"));
        int id_eje = Integer.parseInt(request.getParameter("id_eje"));

        Integer series = null;
        Integer repes = null;
        String tiempo = request.getParameter("tiempo");
        String metodo = request.getParameter("metodo");
            
        System.out.println("Tiempo: " + tiempo);
        try {
            // Check if the 'series' parameter is not empty before parsing
            String seriesParam = request.getParameter("series");
            if (seriesParam != null && !seriesParam.isEmpty()) {
                series = Integer.parseInt(seriesParam);
            }

            // Check if the 'repes' parameter is not empty before parsing
            String repesParam = request.getParameter("repes");
            System.out.println(repesParam);
            if (repesParam != null && !repesParam.isEmpty()) {
                repes = Integer.parseInt(repesParam);
            }
            
        } catch (NumberFormatException e) {
            response.getWriter()
                    .append("Error: Invalid number format. Please ensure series and repetitions are valid integers.");
            return;
        }

        ctrlRutina ctrl = new ctrlRutina();
        Boolean success = false;

        if ("modificar".equals(metodo)) {
            success = ctrl.modificarEjercicioRutina(id_rut, id_eje, series, repes, tiempo);
        } else {
            success = ctrl.agregarEjercicioRutina(id_rut, id_eje, series, repes, tiempo);
        }

        if (success) {
            LinkedList<Map<String, Object>> ejercicios = ctrl.getEjerciciosByRutina(id_rut);
            Rutina r = ctrl.getOne(id_rut);
            request.setAttribute("listaEjercicios", ejercicios);
            request.setAttribute("rutina", r);
            request.getRequestDispatcher("WEB-INF/rutinaEjercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar la rutina.");
        }
    }
}
