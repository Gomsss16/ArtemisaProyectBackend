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
@Table(name = "link")
public class Link {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = true)
	private String titulo;
	@Column(columnDefinition = "TEXT")
	private String descripcion;
	private String enlace;
	
	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private byte[] imagen;
	
	public Link() {
		// TODO Auto-generated constructor stub
	}

	public Link(Long id, String titulo, String descripcion, String enlace, byte[] imagen) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(imagen);
		result = prime * result + Objects.hash(descripcion, enlace, id, titulo);
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
		Link other = (Link) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(enlace, other.enlace)
				&& Objects.equals(id, other.id) && Arrays.equals(imagen, other.imagen)
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", enlace=" + enlace
				+ ", imagen=" + Arrays.toString(imagen) + "]";
	}
	
	

}
