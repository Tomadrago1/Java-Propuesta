package servletEntrenamiento;

import java.io.IOException;
import java.util.LinkedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import entities.Ejercicio;
import entities.Entrenamiento;
import logic.ctrlEjercicios;
import logic.ctrlEntrenamiento;

@WebServlet("/misEntrenamientos")
public class misEntrenamientos extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int idUsuario = Integer.parseInt(request.getParameter("id"));
  
  ctrlEntrenamiento ctrlEnt = new ctrlEntrenamiento();
  ctrlEjercicios ctrlEje = new ctrlEjercicios();
  
  LinkedList<Entrenamiento> entrenamientos = ctrlEnt.getEntrenamientosByUsuario(idUsuario);

  LinkedList<Ejercicio> ejercicios = new LinkedList<Ejercicio>();

  for (Entrenamiento ent : entrenamientos) {
    int id_eje = ent.getIdEjercicio();
    ejercicios.add(ctrlEje.getOne(id_eje));
  }
  request.setAttribute("ejercicios", ejercicios);
  request.setAttribute("entrenamientos", entrenamientos);
  request.getRequestDispatcher("WEB-INF/misEntrenamientos.jsp").forward(request, response);
  }
}
