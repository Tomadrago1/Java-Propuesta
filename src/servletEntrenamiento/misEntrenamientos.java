package servletEntrenamiento;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import entities.Entrenamiento;
import entities.Rutina;
import logic.ctrlEntrenamiento;
import logic.ctrlRutina;

@WebServlet("/misEntrenamientos")
public class misEntrenamientos extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int idUsuario = Integer.parseInt(request.getParameter("id"));
  
  ctrlEntrenamiento ctrlEnt = new ctrlEntrenamiento();
  ctrlRutina ctrlRut = new ctrlRutina();
  
  LinkedList<Entrenamiento> entrenamientos = ctrlEnt.getEntrenamientosByUsuario(idUsuario);

  // Agrupar entrenamientos por rutina y fecha
  Map<String, EntrenamientoAgrupado> entrenamientosAgrupados = new LinkedHashMap<>();
  
  for (Entrenamiento ent : entrenamientos) {
    String key = ent.getIdRutina() + "_" + ent.getFecha().toString();
    
    if (!entrenamientosAgrupados.containsKey(key)) {
      EntrenamientoAgrupado grupo = new EntrenamientoAgrupado();
      grupo.idRutina = ent.getIdRutina();
      grupo.fecha = ent.getFecha();
      grupo.rutina = ctrlRut.getOne(ent.getIdRutina());
      entrenamientosAgrupados.put(key, grupo);
    }
  }
  
  request.setAttribute("entrenamientosAgrupados", entrenamientosAgrupados.values());
  request.getRequestDispatcher("WEB-INF/misEntrenamientos.jsp").forward(request, response);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request, response);
}

  // Clase interna para agrupar entrenamientos
  public static class EntrenamientoAgrupado {
    public int idRutina;
    public LocalDate fecha;
    public Rutina rutina;
  }
}
