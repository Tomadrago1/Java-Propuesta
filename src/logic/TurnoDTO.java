package logic;

import java.time.LocalTime;
import entities.Usuario;

public class TurnoDTO {
    private LocalTime hora;
    private String estado;
    private Usuario cliente;

    public TurnoDTO(LocalTime hora, String estado, Usuario cliente) {
        this.hora = hora;
        this.estado = estado;
        this.cliente = cliente;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getCliente() {
        return cliente;
    }
}
