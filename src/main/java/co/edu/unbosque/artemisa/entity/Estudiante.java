package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiantes")
@PrimaryKeyJoinColumn(name = "id")
public class Estudiante extends Usuario {

	public Estudiante() {
		// TODO Auto-generated constructor stub
	}

	@Lob
	@Column(name = "imagen_perfil", columnDefinition = "LONGTEXT")
	private String imagenPerfil; // Base64 de la imagen

	// Agregar getter y setter
	public String getImagenPerfil() { return imagenPerfil; }
	public void setImagenPerfil(String imagenPerfil) { this.imagenPerfil = imagenPerfil; }

	
	public Estudiante(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
	}



	@Override
	public String toString() {
		return super.toString() + "Estudiantes : ";
	}
	
}
