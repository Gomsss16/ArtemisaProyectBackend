package co.edu.unbosque.artemisa.entity;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Entidad que representa un libro dentro del sistema.
 * <p>
 * Cada libro contiene información básica como título, autor, descripción,
 * enlace externo y puede almacenar tanto un archivo PDF como una imagen de
 * portada.
 * </p>
 */
@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Título único del libro.
	 */
	@Column(unique = true, nullable = false)
	private String titulo;

	/**
	 * Autor del libro.
	 */
	private String author;

	/**
	 * Descripción del libro (larga).
	 */
	@Column(columnDefinition = "TEXT")
	private String descripcion;

	/**
	 * Enlace externo relacionado al libro.
	 */
	private String enlace;

	/**
	 * Archivo PDF del libro.
	 */
	@Lob
	@Column(name = "pdf", columnDefinition = "LONGBLOB")
	private byte[] pdf;

	/**
	 * Imagen de portada del libro.
	 */
	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private byte[] imagen;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Libro() {
	}

	/**
	 * Constructor completo.
	 */
	public Libro(Long id, String titulo, String author, String descripcion, String enlace, byte[] pdf, byte[] imagen) {
		this.id = id;
		this.titulo = titulo;
		this.author = author;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.pdf = pdf;
		this.imagen = imagen;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id, titulo, author, descripcion, enlace) + Arrays.hashCode(pdf) + Arrays.hashCode(imagen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Libro other))
			return false;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(author, other.author) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(enlace, other.enlace) && Arrays.equals(pdf, other.pdf)
				&& Arrays.equals(imagen, other.imagen);
	}

	// ================= ToString =================

	@Override
	public String toString() {
		return "Libro{id=" + id + ", titulo='" + titulo + '\'' + ", author='" + author + '\'' + ", descripcion='"
				+ descripcion + '\'' + ", enlace='" + enlace + '\'' + ", pdf="
				+ (pdf != null ? pdf.length + " bytes" : "null") + ", imagen="
				+ (imagen != null ? imagen.length + " bytes" : "null") + '}';
	}
}
