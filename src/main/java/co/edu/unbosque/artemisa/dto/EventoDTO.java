package co.edu.unbosque.artemisa.dto;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data Transfer Object (DTO) para representar un evento dentro del sistema.
 * <p>
 * Se utiliza para transportar información de eventos entre las diferentes capas
 * de la aplicación, sin exponer directamente la entidad de persistencia.
 * </p>
 */
public class EventoDTO {

	/**
	 * Identificador único del evento.
	 */
	private Long id;

	/**
	 * Título del evento.
	 */
	private String titulo;

	/**
	 * Descripción detallada del evento.
	 */
	private String descripcion;

	/**
	 * Tipo del evento (ejemplo: académico, social, institucional).
	 */
	private String tipo;

	/**
	 * Fecha y hora del evento. El formato JSON será {@code yyyy-MM-dd HH:mm:ss}.
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;

	/**
	 * Enlace de acceso o recurso asociado al evento (ejemplo: link de Zoom).
	 */
	private String enlace;

	/**
	 * Ubicación física o virtual del evento.
	 */
	private String ubicacion;

	/**
	 * Constructor vacío requerido para serialización y deserialización.
	 */
	public EventoDTO() {
	}

	/**
	 * Constructor sin id (para crear nuevos eventos).
	 *
	 * @param titulo      título del evento
	 * @param descripcion descripción detallada
	 * @param tipo        tipo del evento
	 * @param fecha       fecha y hora del evento
	 * @param enlace      enlace asociado
	 * @param ubicacion   ubicación del evento
	 */
	public EventoDTO(String titulo, String descripcion, String tipo, Date fecha, String enlace, String ubicacion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.enlace = enlace;
		this.ubicacion = ubicacion;
	}

	/**
	 * Constructor con todos los campos, incluido el id.
	 *
	 * @param id          identificador único
	 * @param titulo      título del evento
	 * @param descripcion descripción detallada
	 * @param tipo        tipo del evento
	 * @param fecha       fecha y hora del evento
	 * @param enlace      enlace asociado
	 * @param ubicacion   ubicación del evento
	 */
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

	/**
	 * Obtiene el identificador único del evento.
	 *
	 * @return id del evento
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna un identificador único al evento.
	 *
	 * @param id nuevo id del evento
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del evento.
	 *
	 * @return título del evento
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título del evento.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción del evento.
	 *
	 * @return descripción del evento
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del evento.
	 *
	 * @param descripcion nueva descripción
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el tipo del evento.
	 *
	 * @return tipo del evento
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo del evento.
	 *
	 * @param tipo nuevo tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene la fecha y hora del evento.
	 *
	 * @return fecha del evento
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha y hora del evento.
	 *
	 * @param fecha nueva fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene el enlace asociado al evento.
	 *
	 * @return enlace del evento
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * Establece el enlace asociado al evento.
	 *
	 * @param enlace nuevo enlace
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Obtiene la ubicación del evento.
	 *
	 * @return ubicación del evento
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Establece la ubicación del evento.
	 *
	 * @param ubicacion nueva ubicación
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Calcula el hash en base a los atributos principales.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, enlace, fecha, id, tipo, titulo, ubicacion);
	}

	/**
	 * Compara si dos objetos EventoDTO son iguales.
	 *
	 * @param obj objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
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

	/**
	 * Devuelve una representación en cadena del evento.
	 *
	 * @return cadena con los datos del evento
	 */
	@Override
	public String toString() {
		return "EventoDTO [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", fecha=" + fecha + ", enlace=" + enlace + ", ubicacion=" + ubicacion + "]";
	}
}
