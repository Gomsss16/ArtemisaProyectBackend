package co.edu.unbosque.artemisa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Clase abstracta que representa un usuario dentro del sistema.
 * <p>
 * Esta entidad es la base de herencia para diferentes tipos de usuarios como
 * {@link Estudiante} y {@link Profesor}. Se almacenan datos básicos de acceso y
 * perfil.
 * </p>
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre de usuario único en el sistema.
	 */
	@Column(unique = true, name = "usuario")
	private String usuario;

	/**
	 * Contraseña de acceso del usuario.
	 */
	private String contrasenia;

	/**
	 * Nivel de permisos asignado al usuario. Puede representar roles como "admin",
	 * "profesor", "estudiante", etc.
	 */
	private String nivelDePermiso;

	/**
	 * Fecha de nacimiento del usuario.
	 */
	private String fechaDeNacimiento;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Usuario() {
		// Constructor por defecto
	}

	/**
	 * Constructor con parámetros para inicializar todos los atributos.
	 *
	 * @param id                identificador único
	 * @param usuario           nombre de usuario
	 * @param contrasenia       contraseña de acceso
	 * @param nivelDePermiso    rol o permisos del usuario
	 * @param fechaDeNacimiento fecha de nacimiento
	 */
	public Usuario(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nivelDePermiso = nivelDePermiso;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Obtiene el identificador único del usuario.
	 *
	 * @return id del usuario
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna un nuevo identificador al usuario.
	 *
	 * @param id identificador único
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
	 * Obtiene la contraseña del usuario.
	 *
	 * @return contraseña
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasenia nueva contraseña
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Obtiene el nivel de permisos del usuario.
	 *
	 * @return nivel de permisos
	 */
	public String getNivelDePermiso() {
		return nivelDePermiso;
	}

	/**
	 * Establece el nivel de permisos del usuario.
	 *
	 * @param nivelDePermiso nuevo nivel de permisos
	 */
	public void setNivelDePermiso(String nivelDePermiso) {
		this.nivelDePermiso = nivelDePermiso;
	}

	/**
	 * Obtiene la fecha de nacimiento del usuario.
	 *
	 * @return fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Establece la fecha de nacimiento del usuario.
	 *
	 * @param fechaDeNacimiento nueva fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Representación en texto del objeto Usuario.
	 *
	 * @return cadena con los atributos básicos del usuario
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", Contraseña=" + contrasenia + ", fechaDeNacimiento="
				+ fechaDeNacimiento + "]";
	}

	/**
	 * Genera el hash del objeto.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, fechaDeNacimiento, id, nivelDePermiso, usuario);
	}

	/**
	 * Compara si dos objetos Usuario son iguales.
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
		Usuario other = (Usuario) obj;
		return Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nivelDePermiso, other.nivelDePermiso) && Objects.equals(usuario, other.usuario);
	}
}
