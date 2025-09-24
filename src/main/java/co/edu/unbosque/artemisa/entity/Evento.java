package co.edu.unbosque.artemisa.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String titulo;

	@Column(columnDefinition = "TEXT")
	private String descripcion;

	private String tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String enlace;

	private String ubicacion;

	public Evento() {
	}

	public Evento(Long id, String titulo, String descripcion, String tipo, Date fecha, String enlace,
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
		Evento other = (Evento) obj;
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
		return "Evento [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", fecha=" + fecha + ", enlace=" + enlace + ", ubicacion=" + ubicacion + "]";
	}
}
