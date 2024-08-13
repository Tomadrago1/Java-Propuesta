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
        int series = Integer.parseInt(request.getParameter("series"));
        int repes = Integer.parseInt(request.getParameter("repes"));
        
        ctrlRutina ctrl = new ctrlRutina();
        
        Boolean success = ctrl.modificarEjercicioRutina(id_rut, id_eje, series, repes);

        if (success) {
        	LinkedList<Map<String,Object>> ejercicios = ctrl.getEjerciciosByRutina(id_rut);
        	Rutina r = ctrl.getOne(id_rut);
            request.setAttribute("listaEjercicios", ejercicios);
            request.setAttribute("rutina", r);
            request.getRequestDispatcher("WEB-INF/rutinaEjercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }
}
