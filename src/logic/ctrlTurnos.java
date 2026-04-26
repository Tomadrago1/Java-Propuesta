package logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import data.DaoConsulta;
import data.DaoHorario;
import entities.Consulta;
import entities.Horario;

public class ctrlTurnos {

    private DaoHorario daoH;
    private DaoConsulta daoC;

    public ctrlTurnos() {
        this.daoH = new DaoHorario();
        this.daoC = new DaoConsulta();
    }

    public LinkedList<LocalTime> getTurnosDisponibles(int idProfesional, LocalDate fecha) {
        LinkedList<Horario> horarios = daoH.getHorariosPorProfesionalYFecha(idProfesional, fecha);
        LinkedList<Consulta> consultas = daoC.getConsultasPorProfesionalYFecha(idProfesional, fecha);

        LinkedList<LocalTime> turnosDisponibles = new LinkedList<>();

        for (Horario h : horarios) {
            LocalDateTime inicio = h.getFecha_hora_desde();
            LocalDateTime fin = h.getFecha_hora_hasta();

            while (inicio.isBefore(fin)) {
                boolean ocupado = false;
                for (Consulta c : consultas) {
                    // Check if this slot is already booked and not cancelled
                    if (c.getFecha_consulta().equals(inicio) && 
                       (c.getEstado() == null || !c.getEstado().equalsIgnoreCase("Cancelado"))) {
                        ocupado = true;
                        break;
                    }
                }

                if (!ocupado) {
                    turnosDisponibles.add(inicio.toLocalTime());
                }

                // Incrementar media hora
                inicio = inicio.plusMinutes(30);
            }
        }

        return turnosDisponibles;
    }
    
    public boolean agendarTurno(Consulta c) {
        return daoC.agregarConsulta(c);
    }
    
    public LinkedList<TurnoDTO> getAgendaDiaria(int idProfesional, LocalDate fecha) {
        LinkedList<Horario> horarios = daoH.getHorariosPorProfesionalYFecha(idProfesional, fecha);
        LinkedList<Consulta> consultas = daoC.getConsultasPorProfesionalYFecha(idProfesional, fecha);

        LinkedList<TurnoDTO> agenda = new LinkedList<>();

        for (Horario h : horarios) {
            LocalDateTime inicio = h.getFecha_hora_desde();
            LocalDateTime fin = h.getFecha_hora_hasta();

            while (inicio.isBefore(fin)) {
                boolean ocupado = false;
                Consulta consultaEncontrada = null;
                
                for (Consulta c : consultas) {
                    if (c.getFecha_consulta().equals(inicio) && 
                       (c.getEstado() == null || !c.getEstado().equalsIgnoreCase("Cancelado"))) {
                        ocupado = true;
                        consultaEncontrada = c;
                        break;
                    }
                }

                if (ocupado) {
                    agenda.add(new TurnoDTO(inicio.toLocalTime(), "Ocupado", consultaEncontrada.getCliente()));
                } else {
                    agenda.add(new TurnoDTO(inicio.toLocalTime(), "Libre", null));
                }

                inicio = inicio.plusMinutes(30);
            }
        }
        
        // Sort agenda by time
        agenda.sort((t1, t2) -> t1.getHora().compareTo(t2.getHora()));

        return agenda;
    }
    
    public boolean agregarDisponibilidad(Horario h) {
        return daoH.agregarHorario(h);
    }
}
