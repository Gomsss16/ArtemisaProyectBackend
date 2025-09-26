package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad que representa a un estudiante dentro del sistema.
 * <p>
 * Extiende de {@link Usuario}, heredando sus atributos principales y agregando
 * la posibilidad de almacenar una imagen de perfil.
 * </p>
 */
@Entity
@Table(name = "estudiantes")
@PrimaryKeyJoinColumn(name = "id")
public class Estudiante extends Usuario {

	/**
	 * Imagen de perfil del estudiante codificada en Base64. Se almacena como un
	 * campo de tipo {@code LONGTEXT} en la base de datos.
	 */
	@Lob
	@Column(name = "imagen_perfil", columnDefinition = "LONGTEXT")
	private String imagenPerfil;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Estudiante() {
		// Constructor por defecto
	}

	/**
	 * Constructor con parámetros heredados de Usuario.
	 *
	 * @param id                Identificador único del estudiante.
	 * @param usuario           Nombre de usuario.
	 * @param contrasenia       Contraseña asociada.
	 * @param nivelDePermiso    Nivel de permisos del estudiante.
	 * @param fechaDeNacimiento Fecha de nacimiento.
	 */
	public Estudiante(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	@Override
	public String toString() {
		return super.toString() + " Estudiante{imagenPerfil=" + (imagenPerfil != null ? "sí" : "no") + "}";
	}
}
