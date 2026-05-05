package servletRutina;

import java.io.IOException;
import java.util.LinkedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.EjercicioRutina;
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
        
        try {
            // Check if the 'series' parameter is not empty before parsing
            String seriesParam = request.getParameter("series");
            if (!seriesParam.equals("")) {
                series = Integer.parseInt(seriesParam);
            }

            // Check if the 'repes' parameter is not empty before parsing
            String repesParam = request.getParameter("repes");
            if (!repesParam.equals("")) {
                repes = Integer.parseInt(repesParam);
            }
            
        } catch (NumberFormatException e) {
            response.getWriter().append(e.getMessage());
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
            LinkedList<EjercicioRutina> ejercicios_rutina = ctrl.getEjerciciosByRutina(id_rut);
            request.setAttribute("ejercicios_rutina", ejercicios_rutina);
            request.getRequestDispatcher("WEB-INF/rutinaEjercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar la rutina.");
        }
    }
}
