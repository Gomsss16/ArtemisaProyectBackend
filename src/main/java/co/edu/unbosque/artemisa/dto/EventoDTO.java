package co.edu.unbosque.artemisa.dto;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private String tipo;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;

	private String enlace;
	private String ubicacion;

	public EventoDTO() {
	}

	public EventoDTO(String titulo, String descripcion, String tipo, Date fecha, String enlace, String ubicacion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.enlace = enlace;
		this.ubicacion = ubicacion;
	}

	public EventoDTO(Long id, String titulo, String descripcion, String tipo, Date fecha, String enlace,
			String ubicacion) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.enlace = enlace;
		this.ubicacion = ubicacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, enlace, fecha, id, tipo, titulo, ubicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoDTO other = (EventoDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(enlace, other.enlace)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id)
				&& Objects.equals(tipo, other.tipo) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(ubicacion, other.ubicacion);
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "EventoDTO [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", fecha=" + fecha + ", enlace=" + enlace + ", ubicacion=" + ubicacion + "]";
	}
}
