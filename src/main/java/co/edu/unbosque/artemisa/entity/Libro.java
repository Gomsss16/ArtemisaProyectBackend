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

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = true)
	private String titulo;
	private String autor;
	@Column(columnDefinition = "TEXT")
	private String descripcion;

	@Lob
	@Column(name = "pdf", columnDefinition = "LONGBLOB")
	private byte[] pdf;

	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private byte[] imagen;

	public Libro() {
		// TODO Auto-generated constructor stub
	}
	
	public Libro(Long id, String titulo, String autor, String descripcion, byte[] pdf, byte[] imagen) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		this.pdf = pdf;
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(imagen);
		result = prime * result + Arrays.hashCode(pdf);
		result = prime * result + Objects.hash(autor, descripcion, id, titulo);
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
		return Objects.equals(autor, other.autor) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id) && Arrays.equals(imagen, other.imagen) && Arrays.equals(pdf, other.pdf)
				&& Objects.equals(titulo, other.titulo);
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", descripcion=" + descripcion + ", pdf="
				+ Arrays.toString(pdf) + ", imagen=" + Arrays.toString(imagen) + "]";
	}
	
	
	
	
	

}
