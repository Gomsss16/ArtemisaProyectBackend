package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad que representa a un profesor en el sistema.
 * <p>
 * Hereda de {@link Usuario} y añade el atributo de imagen de perfil como dato
 * adicional.
 * </p>
 */
@Entity
@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "id")
public class Profesor extends Usuario {

	/**
	 * Imagen de perfil del profesor en formato Base64 o cadena larga.
	 */
	@Lob
	@Column(name = "imagen_perfil", columnDefinition = "LONGTEXT")
	private String imagenPerfil;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Profesor() {
		// Constructor por defecto
	}

	/**
	 * Constructor para inicializar un profesor con los datos principales.
	 *
	 * @param id                Identificador único
	 * @param usuario           Nombre de usuario
	 * @param contrasenia       Contraseña encriptada o en texto
	 * @param nivelDePermiso    Nivel de permisos del usuario
	 * @param fechaDeNacimiento Fecha de nacimiento del profesor
	 */
	public Profesor(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
	}

	/**
	 * Obtiene la imagen de perfil del profesor.
	 *
	 * @return imagen en formato cadena larga
	 */
	public String getImagenPerfil() {
		return imagenPerfil;
	}

	/**
	 * Asigna la imagen de perfil del profesor.
	 *
	 * @param imagenPerfil nueva imagen en formato cadena larga
	 */
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	/**
	 * Representación en texto del objeto Profesor.
	 *
	 * @return cadena con los datos principales del profesor
	 */
	@Override
	public String toString() {
		return super.toString() + "Profesor : ";
	}
}
