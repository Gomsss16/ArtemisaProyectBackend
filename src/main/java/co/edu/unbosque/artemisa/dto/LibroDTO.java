package co.edu.unbosque.artemisa.dto;

import java.util.Arrays;
import java.util.Objects;


public class LibroDTO {
	
	private Long id;
	private String titulo;
	private String autor;
	private String descripcion;
	private byte[] pdf;
	private byte[] imagen;
	
	public LibroDTO() {
		// TODO Auto-generated constructor stub
	}

	public LibroDTO(Long id, String titulo, String autor, String descripcion, byte[] pdf, byte[] imagen) {
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
		LibroDTO other = (LibroDTO) obj;
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
		return "LibroDTO [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", descripcion=" + descripcion
				+ ", pdf=" + Arrays.toString(pdf) + ", imagen=" + Arrays.toString(imagen) + "]";
	}
	

}
