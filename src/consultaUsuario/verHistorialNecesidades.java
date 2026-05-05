package consultaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Necesidad;
import entities.Usuario;
import logic.ctrlNecesidad;

@WebServlet("/verHistorialNecesidades")
public class verHistorialNecesidades extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        // Asegurarse de que sea un cliente (tipo 3)
        if (u == null || u.getTipoUsu() != 3) {
            response.sendRedirect("index.html");
            return;
        }

        try {
            ctrlNecesidad ctrl = new ctrlNecesidad();
            LinkedHashMap<LocalDate, LinkedList<Necesidad>> historial = ctrl.getHistorialNecesidades(u.getIdUsuario());
            
            request.setAttribute("historial", historial);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/historialNecesidades.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signin");
        }
    }
}
