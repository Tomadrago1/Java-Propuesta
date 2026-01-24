package servletEntrenamiento;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ejercicio;
import entities.EjercicioRutina;
import entities.Entrenamiento;
import entities.Rutina;
import entities.Usuario;
import logic.ctrlEntrenamiento;
import logic.ctrlRutina;
import logic.ctrlUsuario;

@WebServlet("/guardarEntrenamiento")
public class guardarEntrenamiento extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idRutina = Integer.parseInt(request.getParameter("id_rutina"));
    int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));

    ctrlRutina ctrlRut = new ctrlRutina();
    LinkedList<EjercicioRutina> ejerciciosRutina = ctrlRut.getEjerciciosByRutina(idRutina);
    LinkedList<Ejercicio> ejercicios = new LinkedList<Ejercicio>();

    for (EjercicioRutina ejRut : ejerciciosRutina) {
      ejercicios.add(ejRut.getEjercicio());
    }

    ctrlEntrenamiento ctrlEnt = new ctrlEntrenamiento();

    for (Ejercicio ej : ejercicios) {

      String seriesStr = request.getParameter("series_" + ej.getId());
      String repesStr = request.getParameter("repes_" + ej.getId());
      String tiempoStr = request.getParameter("tiempo_" + ej.getId());
      String pesoStr = request.getParameter("peso_" + ej.getId());

      System.out.println(seriesStr); 
      System.out.println(repesStr);
      System.out.println(tiempoStr);
      System.out.println(pesoStr);

      int series = (seriesStr != null && !seriesStr.isEmpty()) ? Integer.parseInt(seriesStr) : 0;
      int repes = (repesStr != null && !repesStr.isEmpty()) ? Integer.parseInt(repesStr) : 0;
      String tiempo = (tiempoStr != null) ? tiempoStr : "";
      double peso = (pesoStr != null && !pesoStr.isEmpty()) ? Double.parseDouble(pesoStr) : 0.0;

      LocalDate fechaActual = LocalDate.now();

      Entrenamiento ent = new Entrenamiento(ej.getId(), idRutina, idUsuario, fechaActual, series, repes, tiempo, peso);
      ctrlEnt.guardarEntrenamiento(ent);

    }
    
    ctrlRutina ctrlRutina = new ctrlRutina();
    ctrlUsuario ctrlU = new ctrlUsuario();

    Usuario u = ctrlU.getUserById(idUsuario);
    LinkedList<Rutina> rutinas = ctrlRutina.getRutinaByUsuario(idUsuario);

    request.setAttribute("rutinas", rutinas);
    request.setAttribute("usuario", u);
    request.getRequestDispatcher("WEB-INF/misRutinas.jsp").forward(request, response);
  }
}
