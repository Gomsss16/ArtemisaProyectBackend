package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa un enlace dentro del sistema.
 * <p>
 * Se utiliza para transportar información de enlaces entre las diferentes capas
 * de la aplicación, evitando exponer directamente la entidad de persistencia.
 * Incluye título, descripción, enlace y una imagen codificada en Base64.
 * </p>
 */
public class LinkDTO {

	/**
	 * Identificador único del enlace.
	 */
	private Long id;

	/**
	 * Título del enlace.
	 */
	private String titulo;

	/**
	 * Breve descripción del enlace.
	 */
	private String descripcion;

	/**
	 * Dirección del enlace (ejemplo: URL de referencia).
	 */
	private String enlace;

	/**
	 * Imagen asociada al enlace, codificada en Base64.
	 */
	private String imagenBase64;

	/**
	 * Constructor vacío requerido para serialización y deserialización.
	 */
	public LinkDTO() {
		// Constructor por defecto
	}

	/**
	 * Constructor con todos los campos.
	 *
	 * @param id           identificador único
	 * @param titulo       título del enlace
	 * @param descripcion  descripción del enlace
	 * @param enlace       URL del enlace
	 * @param imagenBase64 imagen en Base64 asociada
	 */
	public LinkDTO(Long id, String titulo, String descripcion, String enlace, String imagenBase64) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.imagenBase64 = imagenBase64;
	}

	/**
	 * Obtiene el identificador único del enlace.
	 *
	 * @return id del enlace
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del enlace.
	 *
	 * @param id nuevo id del enlace
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del enlace.
	 *
	 * @return título del enlace
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título del enlace.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción del enlace.
	 *
	 * @return descripción del enlace
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del enlace.
	 *
	 * @param descripcion nueva descripción
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene la dirección del enlace.
	 *
	 * @return URL del enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * Establece la dirección del enlace.
	 *
	 * @param enlace nueva URL
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Obtiene la imagen asociada al enlace en formato Base64.
	 *
	 * @return imagen en Base64
	 */
	public String getImagenBase64() {
		return imagenBase64;
	}

	/**
	 * Establece la imagen asociada al enlace en formato Base64.
	 *
	 * @param imagenBase64 nueva imagen en Base64
	 */
	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}

	/**
	 * Calcula el hash basado en los atributos del enlace.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, enlace, id, imagenBase64, titulo);
	}

	/**
	 * Compara si dos objetos LinkDTO son iguales.
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
		LinkDTO other = (LinkDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(enlace, other.enlace)
				&& Objects.equals(id, other.id) && Objects.equals(imagenBase64, other.imagenBase64)
				&& Objects.equals(titulo, other.titulo);
	}

	/**
	 * Devuelve una representación en cadena del enlace.
	 *
	 * @return cadena con los datos del enlace
	 */
	@Override
	public String toString() {
		return "LinkDTO [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", enlace=" + enlace
				+ ", imagenBase64=" + imagenBase64 + "]";
	}
}
