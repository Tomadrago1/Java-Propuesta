package servletEntrenamiento;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import entities.Ejercicio;
import entities.Entrenamiento;
import entities.Rutina;
import logic.ctrlEjercicios;
import logic.ctrlEntrenamiento;
import logic.ctrlRutina;

@WebServlet("/verDetalleEntrenamiento")
public class verDetalleEntrenamiento extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idRutina = Integer.parseInt(request.getParameter("idRutina"));
    String fechaHoraStr = request.getParameter("fechaHora");
    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    
    ctrlEntrenamiento ctrlEnt = new ctrlEntrenamiento();
    ctrlEjercicios ctrlEje = new ctrlEjercicios();
    ctrlRutina ctrlRut = new ctrlRutina();
    
    // Obtener todos los entrenamientos del usuario
    LinkedList<Entrenamiento> todosEntrenamientos = ctrlEnt.getEntrenamientosByUsuario(idUsuario);
    
    // Filtrar por rutina y fechaHora
    LinkedList<Entrenamiento> entrenamientos = new LinkedList<>();
    LinkedList<Ejercicio> ejercicios = new LinkedList<>();
    
    for (Entrenamiento ent : todosEntrenamientos) {
      if (ent.getIdRutina() == idRutina && ent.getFechaHora() != null && ent.getFechaHora().equals(fechaHora)) {
        entrenamientos.add(ent);
        ejercicios.add(ctrlEje.getOne(ent.getIdEjercicio()));
      }
    }
    
    Rutina rutina = ctrlRut.getOne(idRutina);
    
    request.setAttribute("ejercicios", ejercicios);
    request.setAttribute("entrenamientos", entrenamientos);
    request.setAttribute("rutina", rutina);
    request.setAttribute("fechaHora", fechaHora);
    request.getRequestDispatcher("WEB-INF/detalleEntrenamiento.jsp").forward(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
