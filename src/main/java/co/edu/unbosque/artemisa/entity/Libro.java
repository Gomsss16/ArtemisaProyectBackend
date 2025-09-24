package co.edu.unbosque.artemisa.entity;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String titulo;

	private String author;

	@Column(columnDefinition = "TEXT")
	private String descripcion;

	private String coverUrl;
	private String bookUrl;
	private String onlineUrl;

	@Lob
	@Column(name = "pdf", columnDefinition = "LONGBLOB")
	private byte[] pdf;

	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private byte[] imagen;

	public Libro() {
	}

	public Libro(String titulo, String author, String descripcion, String coverUrl, String bookUrl,
			String onlineUrl, byte[] pdf, byte[] imagen) {
		this.id = id;
		this.titulo = titulo;
		this.author = author;
		this.descripcion = descripcion;
		this.coverUrl = coverUrl;
		this.bookUrl = bookUrl;
		this.onlineUrl = onlineUrl;
		this.pdf = pdf;
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(imagen);
		result = prime * result + Arrays.hashCode(pdf);
		result = prime * result + Objects.hash(author, descripcion, id, titulo, coverUrl, bookUrl, onlineUrl);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(author, other.author) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id) && Arrays.equals(imagen, other.imagen) && Arrays.equals(pdf, other.pdf)
				&& Objects.equals(titulo, other.titulo) && Objects.equals(coverUrl, other.coverUrl)
				&& Objects.equals(bookUrl, other.bookUrl) && Objects.equals(onlineUrl, other.onlineUrl);
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

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getOnlineUrl() {
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
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
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", author=" + author + ", descripcion=" + descripcion
				+ ", coverUrl=" + coverUrl + ", bookUrl=" + bookUrl + ", onlineUrl=" + onlineUrl + ", pdf="
				+ Arrays.toString(pdf) + ", imagen=" + Arrays.toString(imagen) + "]";
	}
}
