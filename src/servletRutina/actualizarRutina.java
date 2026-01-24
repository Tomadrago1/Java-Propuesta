package servletRutina;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Rutina;
import logic.ctrlRutina;

// Aca se modifica el rutina y vuelve a la lista de rutinas

@WebServlet("/actualizarRutina")
public class actualizarRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String nombre = request.getParameter("nombre");
        String desc = request.getParameter("descripcion");

    	ctrlRutina ctrl = new ctrlRutina();
        
        Boolean success= ctrl.modificarRutina(id, nombre, desc);

        if (success) {
    		LinkedList<Rutina> rutinas = ctrl.getAll();
            request.setAttribute("listaRutinas", rutinas);
            request.getRequestDispatcher("WEB-INF/Rutina/rutinaManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el rutina.");
        }
    }
}