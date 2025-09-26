package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa a un profesor en el sistema.
 * <p>
 * Se utiliza para transportar la información del profesor entre las distintas
 * capas de la aplicación, evitando exponer directamente la entidad de
 * persistencia.
 * </p>
 */
public class ProfesorDTO {

	/**
	 * Identificador único del profesor.
	 */
	private Long id;

	/**
	 * Nombre de usuario asociado al profesor.
	 */
	private String usuario;

	/**
	 * Contraseña del profesor (almacenada de forma segura en capas posteriores).
	 */
	private String contrasenia;

	/**
	 * Nivel de permiso que determina los privilegios del profesor en el sistema.
	 */
	private String nivelDePermiso;

	/**
	 * Fecha de nacimiento del profesor.
	 */
	private String fechaDeNacimiento;

	/**
	 * Imagen de perfil codificada en Base64.
	 */
	private String imagenPerfil;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public ProfesorDTO() {
		// Constructor por defecto
	}

	/**
	 * Constructor con todos los atributos excepto imagen.
	 *
	 * @param id                identificador único
	 * @param usuario           nombre de usuario
	 * @param contrasenia       contraseña
	 * @param nivelDePermiso    permisos asignados
	 * @param fechaDeNacimiento fecha de nacimiento
	 */
	public ProfesorDTO(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nivelDePermiso = nivelDePermiso;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Obtiene el identificador único del profesor.
	 *
	 * @return id del profesor
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del profesor.
	 *
	 * @param id nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de usuario del profesor.
	 *
	 * @return nombre de usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre de usuario del profesor.
	 *
	 * @param usuario nuevo nombre de usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene la contraseña del profesor.
	 *
	 * @return contraseña
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Establece la contraseña del profesor.
	 *
	 * @param contrasenia nueva contraseña
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Obtiene el nivel de permiso del profesor.
	 *
	 * @return nivel de permiso
	 */
	public String getNivelDePermiso() {
		return nivelDePermiso;
	}

	/**
	 * Establece el nivel de permiso del profesor.
	 *
	 * @param nivelDePermiso nuevo nivel de permiso
	 */
	public void setNivelDePermiso(String nivelDePermiso) {
		this.nivelDePermiso = nivelDePermiso;
	}

	/**
	 * Obtiene la fecha de nacimiento del profesor.
	 *
	 * @return fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del profesor.
	 *
	 * @param fechaDeNacimiento nueva fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Obtiene la imagen de perfil codificada en Base64.
	 *
	 * @return imagen de perfil en Base64
	 */
	public String getImagenPerfil() {
		return imagenPerfil;
	}

	/**
	 * Establece la imagen de perfil codificada en Base64.
	 *
	 * @param imagenPerfil nueva imagen en Base64
	 */
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	/**
	 * Calcula el hash basado en los atributos del profesor.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, fechaDeNacimiento, id, nivelDePermiso, usuario);
	}

	/**
	 * Compara si dos objetos ProfesorDTO son iguales.
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
		ProfesorDTO other = (ProfesorDTO) obj;
		return Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nivelDePermiso, other.nivelDePermiso) && Objects.equals(usuario, other.usuario);
	}

	/**
	 * Devuelve una representación en cadena del profesor.
	 *
	 * @return cadena con los datos del profesor
	 */
	@Override
	public String toString() {
		return "ProfesorDTO [id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", nivelDePermiso="
				+ nivelDePermiso + ", fechaDeNacimiento=" + fechaDeNacimiento + "]";
	}
}
