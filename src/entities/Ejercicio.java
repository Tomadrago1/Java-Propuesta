package entities;

public class Ejercicio {
	private int id;
	private String nombre;
	private String descripcion;
	private String zona;
	private String tipoEjercicio;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getTipoEjercicio() {
		return tipoEjercicio;
	}

	public void setTipoEjercicio(String tipoEjercicio) {
		this.tipoEjercicio = tipoEjercicio;
	}

	@Override
	public String toString() {
		return "Ejercicio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion 
				+ ", zona=" + zona + ", tipoEjercicio=" + tipoEjercicio + "]";
	}
	
	
}
