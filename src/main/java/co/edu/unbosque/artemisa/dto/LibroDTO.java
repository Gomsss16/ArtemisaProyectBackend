package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa un libro dentro del sistema.
 * <p>
 * Se utiliza para transportar información de libros entre las diferentes capas
 * de la aplicación, evitando exponer directamente la entidad de persistencia.
 * Incluye además representaciones en Base64 de la imagen y el PDF.
 * </p>
 */
public class LibroDTO {

	/**
	 * Identificador único del libro.
	 */
	private Long id;

	/**
	 * Título del libro.
	 */
	private String titulo;

	/**
	 * Autor del libro.
	 */
	private String author;

	/**
	 * Descripción o sinopsis del libro.
	 */
	private String descripcion;

	/**
	 * Enlace asociado al libro (ejemplo: URL de referencia o descarga).
	 */
	private String enlace;

	/**
	 * Imagen del libro codificada en Base64.
	 */
	private String imagenBase64;

	/**
	 * Documento PDF del libro codificado en Base64.
	 */
	private String pdfBase64;

	/**
	 * Constructor vacío requerido para serialización y deserialización.
	 */
	public LibroDTO() {
		// Constructor por defecto
	}

	/**
	 * Constructor con todos los campos.
	 *
	 * @param id           identificador único
	 * @param titulo       título del libro
	 * @param author       autor del libro
	 * @param descripcion  descripción del libro
	 * @param enlace       enlace asociado
	 * @param imagenBase64 imagen codificada en Base64
	 * @param pdfBase64    PDF codificado en Base64
	 */
	public LibroDTO(Long id, String titulo, String author, String descripcion, String enlace, String imagenBase64,
			String pdfBase64) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.author = author;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.imagenBase64 = imagenBase64;
		this.pdfBase64 = pdfBase64;
	}

	/**
	 * Obtiene el identificador único del libro.
	 *
	 * @return id del libro
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del libro.
	 *
	 * @param id nuevo id del libro
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del libro.
	 *
	 * @return título del libro
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título del libro.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene el autor del libro.
	 *
	 * @return autor del libro
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Establece el autor del libro.
	 *
	 * @param author nuevo autor
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Obtiene la descripción del libro.
	 *
	 * @return descripción del libro
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del libro.
	 *
	 * @param descripcion nueva descripción
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el enlace asociado al libro.
	 *
	 * @return enlace del libro
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * Establece el enlace asociado al libro.
	 *
	 * @param enlace nuevo enlace
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Obtiene la imagen del libro en formato Base64.
	 *
	 * @return imagen en Base64
	 */
	public String getImagenBase64() {
		return imagenBase64;
	}

	/**
	 * Establece la imagen del libro en formato Base64.
	 *
	 * @param imagenBase64 nueva imagen en Base64
	 */
	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}

	/**
	 * Obtiene el PDF del libro en formato Base64.
	 *
	 * @return PDF en Base64
	 */
	public String getPdfBase64() {
		return pdfBase64;
	}

	/**
	 * Establece el PDF del libro en formato Base64.
	 *
	 * @param pdfBase64 nuevo PDF en Base64
	 */
	public void setPdfBase64(String pdfBase64) {
		this.pdfBase64 = pdfBase64;
	}

	/**
	 * Calcula el hash basado en los atributos del libro.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(author, descripcion, enlace, id, imagenBase64, pdfBase64, titulo);
	}

	/**
	 * Compara si dos objetos LibroDTO son iguales.
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
		LibroDTO other = (LibroDTO) obj;
		return Objects.equals(author, other.author) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(enlace, other.enlace) && Objects.equals(id, other.id)
				&& Objects.equals(imagenBase64, other.imagenBase64) && Objects.equals(pdfBase64, other.pdfBase64)
				&& Objects.equals(titulo, other.titulo);
	}

	/**
	 * Devuelve una representación en cadena del libro.
	 *
	 * @return cadena con los datos del libro
	 */
	@Override
	public String toString() {
		return "LibroDTO [id=" + id + ", titulo=" + titulo + ", author=" + author + ", descripcion=" + descripcion
				+ ", enlace=" + enlace + ", imagenBase64=" + imagenBase64 + ", pdfBase64=" + pdfBase64 + "]";
	}
}
