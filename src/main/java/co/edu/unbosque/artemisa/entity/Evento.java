package co.edu.unbosque.artemisa.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Entidad que representa un evento dentro del sistema.
 * <p>
 * Los eventos pueden ser presenciales o virtuales y contienen información como
 * título, descripción, tipo, fecha, enlace (si aplica) y ubicación.
 * </p>
 */
@Entity
@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Título único del evento.
	 */
	@Column(unique = true, nullable = false)
	private String titulo;

	/**
	 * Descripción detallada del evento.
	 */
	@Column(columnDefinition = "TEXT")
	private String descripcion;

	/**
	 * Tipo de evento (ejemplo: conferencia, taller, seminario, etc.).
	 */
	private String tipo;

	/**
	 * Fecha y hora en que ocurre el evento.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/**
	 * Enlace al evento (aplica si es virtual).
	 */
	private String enlace;

	/**
	 * Ubicación del evento (aplica si es presencial).
	 */
	private String ubicacion;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Evento() {
	}

	/**
	 * Constructor con parámetros.
	 */
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
	public int hashCode() {
		return Objects.hash(id, titulo, fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Evento other))
			return false;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(fecha, other.fecha);
	}

	@Override
	public String toString() {
		return "Evento{id=" + id + ", titulo='" + titulo + '\'' + ", tipo='" + tipo + '\'' + ", fecha=" + fecha
				+ ", ubicacion='" + ubicacion + '\'' + ", enlace='" + (enlace != null ? enlace : "N/A") + '\'' + '}';
	}
}
