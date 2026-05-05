package profesional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Necesidad;
import entities.Usuario;
import logic.ctrlNecesidad;

@WebServlet("/guardarNecesidades")
public class guardarNecesidades extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u == null || u.getTipoUsu() != 2) {
            response.sendRedirect("index.html");
            return;
        }

        try {
            int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
            String[] idsNutrientes = request.getParameterValues("id_nutriente");
            String[] mins = request.getParameterValues("min_dinamico");
            String[] maxs = request.getParameterValues("max_dinamico");
            
            LinkedList<Necesidad> lista = new LinkedList<>();
            LocalDate hoy = LocalDate.now();

            if (idsNutrientes != null && mins != null && maxs != null) {
                for (int i = 0; i < idsNutrientes.length; i++) {
                    try {
                        int idNutriente = Integer.parseInt(idsNutrientes[i]);
                        String minStr = mins[i];
                        String maxStr = maxs[i];
                        
                        if (minStr != null && !minStr.trim().isEmpty() && 
                            maxStr != null && !maxStr.trim().isEmpty()) {
                            
                            double min = Double.parseDouble(minStr);
                            double max = Double.parseDouble(maxStr);
                            
                            Necesidad n = new Necesidad();
                            n.setId_usuario(idPaciente);
                            n.setId_profesional(u.getIdUsuario());
                            n.setId_nutriente(idNutriente);
                            n.setFecha(hoy);
                            n.setCantidad_min(min);
                            n.setCantidad_max(max);
                            
                            lista.add(n);
                        }
                    } catch (NumberFormatException nfe) {
                        // Ignorar filas inválidas
                    }
                }
            }

            if (!lista.isEmpty()) {
                ctrlNecesidad ctrl = new ctrlNecesidad();
                ctrl.guardarNecesidades(lista);
                request.getSession().setAttribute("mensajeExito", "Plan nutricional asignado correctamente.");
            }

            // Redirect back to signin (Panel Profesional)
            response.sendRedirect("signin");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signin");
        }
    }
}
