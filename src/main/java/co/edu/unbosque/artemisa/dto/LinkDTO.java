package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

public class LinkDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private String enlace;
	private String imagenBase64;
	



	public LinkDTO() {
		// TODO Auto-generated constructor stub
	}




	public LinkDTO(Long id, String titulo, String descripcion, String enlace, String imagenBase64) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.imagenBase64 = imagenBase64;
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




	public String getImagenBase64() {
		return imagenBase64;
	}




	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}




	@Override
	public int hashCode() {
		return Objects.hash(descripcion, enlace, id, imagenBase64, titulo);
	}




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




	@Override
	public String toString() {
		return "LinkDTO [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", enlace=" + enlace
				+ ", imagenBase64=" + imagenBase64 + "]";
	}

	
}
