package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) para la entidad
 * {@link co.edu.unbosque.artemisa.entity.Estudiante}.
 * <p>
 * Este DTO se utiliza para transferir información de estudiantes entre las
 * capas de la aplicación sin exponer directamente la entidad.
 * </p>
 */
public class EstudianteDTO {

	/**
	 * Identificador único del estudiante.
	 */
	private Long id;

	/**
	 * Nombre de usuario del estudiante.
	 */
	private String usuario;

	/**
	 * Contraseña de acceso del estudiante.
	 */
	private String contrasenia;

	/**
	 * Nivel de permisos del estudiante. Representa el rol o privilegio dentro del
	 * sistema.
	 */
	private String nivelDePermiso;

	/**
	 * Fecha de nacimiento del estudiante.
	 */
	private String fechaDeNacimiento;

	/**
	 * Imagen de perfil del estudiante (codificada en base64 o como cadena).
	 */
	private String imagenPerfil;

	/**
	 * Constructor vacío requerido para instanciación sin datos iniciales.
	 */
	public EstudianteDTO() {
	}

	/**
	 * Constructor que inicializa los atributos principales del estudiante.
	 *
	 * @param id                identificador único
	 * @param usuario           nombre de usuario
	 * @param contrasenia       contraseña de acceso
	 * @param nivelDePermiso    nivel de permisos o rol
	 * @param fechaDeNacimiento fecha de nacimiento
	 */
	public EstudianteDTO(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nivelDePermiso = nivelDePermiso;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Obtiene la imagen de perfil del estudiante.
	 *
	 * @return imagen de perfil en formato cadena
	 */
	public String getImagenPerfil() {
		return imagenPerfil;
	}

	/**
	 * Establece la imagen de perfil del estudiante.
	 *
	 * @param imagenPerfil nueva imagen de perfil
	 */
	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	/**
	 * Obtiene el identificador único.
	 *
	 * @return id del estudiante
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna un identificador único.
	 *
	 * @param id nuevo identificador
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de usuario.
	 *
	 * @return nombre de usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre de usuario.
	 *
	 * @param usuario nuevo nombre de usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene la contraseña.
	 *
	 * @return contraseña
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Establece la contraseña.
	 *
	 * @param contrasenia nueva contraseña
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Obtiene el nivel de permisos.
	 *
	 * @return nivel de permisos
	 */
	public String getNivelDePermiso() {
		return nivelDePermiso;
	}

	/**
	 * Establece el nivel de permisos.
	 *
	 * @param nivelDePermiso nuevo nivel de permisos
	 */
	public void setNivelDePermiso(String nivelDePermiso) {
		this.nivelDePermiso = nivelDePermiso;
	}

	/**
	 * Obtiene la fecha de nacimiento.
	 *
	 * @return fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento.
	 *
	 * @param fechaDeNacimiento nueva fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Calcula el hash del objeto en base a sus atributos principales.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, fechaDeNacimiento, id, nivelDePermiso, usuario);
	}

	/**
	 * Compara dos objetos para determinar si son iguales.
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
		EstudianteDTO other = (EstudianteDTO) obj;
		return Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nivelDePermiso, other.nivelDePermiso) && Objects.equals(usuario, other.usuario);
	}

	/**
	 * Representación en cadena del objeto.
	 *
	 * @return cadena con los valores de los atributos principales
	 */
	@Override
	public String toString() {
		return "EstudianteDTO [id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", nivelDePermiso="
				+ nivelDePermiso + ", fechaDeNacimiento=" + fechaDeNacimiento + "]";
	}
}
