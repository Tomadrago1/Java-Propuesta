package servletEntrenamiento;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ejercicio;
import entities.Rutina;
import logic.ctrlEjercicios;
import logic.ctrlRutina;

@WebServlet("/iniciarEntrenamiento")
public class IniciarEntrenamiento extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idRutina = Integer.parseInt(request.getParameter("id_rut"));
    int idUsuario = Integer.parseInt(request.getParameter("id_usu"));

    ctrlRutina ctrlRut = new ctrlRutina();
    ctrlEjercicios ctrlEj = new ctrlEjercicios();

    LinkedList<Ejercicio> ejercicios = ctrlEj.getEjerciciosByRutina(idRutina);

    // Obtener valores aproximados para series y repeticiones
    Map<Integer, Integer[]> seriesReps = new HashMap<>();
    for (Ejercicio ej : ejercicios) {
      int series = ctrlRut.getSeriesAprox(ej.getId(), idRutina);
      int repes = ctrlRut.getRepsAprox(ej.getId(), idRutina);
      seriesReps.put(ej.getId(), new Integer[] { series, repes });
    }

    Rutina rutina = ctrlRut.getOne(idRutina);

    request.setAttribute("ejercicios", ejercicios);
    request.setAttribute("seriesReps", seriesReps);
    request.setAttribute("rutina", rutina);
    request.setAttribute("id_usuario", idUsuario);
    request.getRequestDispatcher("WEB-INF/iniciarEntrenamiento.jsp").forward(request, response);
  }
}
