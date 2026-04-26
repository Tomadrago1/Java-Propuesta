package entities;

import java.time.LocalDateTime;

public class Horario {
    private int id_horario;
    private int id_profesional;
    private LocalDateTime fecha_hora_desde;
    private LocalDateTime fecha_hora_hasta;

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public LocalDateTime getFecha_hora_desde() {
        return fecha_hora_desde;
    }

    public void setFecha_hora_desde(LocalDateTime fecha_hora_desde) {
        this.fecha_hora_desde = fecha_hora_desde;
    }

    public LocalDateTime getFecha_hora_hasta() {
        return fecha_hora_hasta;
    }

    public void setFecha_hora_hasta(LocalDateTime fecha_hora_hasta) {
        this.fecha_hora_hasta = fecha_hora_hasta;
    }
}
