package logic;

import java.time.LocalTime;
import entities.Consulta;

public class TurnoDTO {
    private LocalTime hora;
    private String estado;
    private Consulta consulta;

    public TurnoDTO(LocalTime hora, String estado, Consulta consulta) {
        this.hora = hora;
        this.estado = estado;
        this.consulta = consulta;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}
