package entities;

import java.time.LocalDateTime;

public class Consulta {
    private int id_cliente;
    private int id_profesional;
    private LocalDateTime fecha_consulta;
    private String desc_resultados;
    private String estado;
    private Usuario cliente;

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public LocalDateTime getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(LocalDateTime fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getDesc_resultados() {
        return desc_resultados;
    }

    public void setDesc_resultados(String desc_resultados) {
        this.desc_resultados = desc_resultados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
