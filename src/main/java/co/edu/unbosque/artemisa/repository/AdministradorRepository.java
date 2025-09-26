package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Administrador;

/**
 * Repositorio JPA para la entidad {@link Administrador}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla de
 * administradores, además de consultas personalizadas basadas en el campo
 * {@code usuario}.
 */
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	/**
	 * Busca un administrador por su nombre de usuario.
	 * 
	 * @param usuario nombre de usuario del administrador a buscar.
	 * @return un {@link Optional} que contiene el administrador si existe, o vacío
	 *         si no se encuentra.
	 */
	Optional<Administrador> findByUsuario(String usuario);

	/**
	 * Elimina un administrador de la base de datos según su nombre de usuario.
	 * 
	 * @param usuario nombre de usuario del administrador a eliminar.
	 */
	void deleteByUsuario(String usuario);

	/**
	 * Verifica si existe un administrador con un nombre de usuario específico.
	 * 
	 * @param usuario nombre de usuario a verificar.
	 * @return {@code true} si existe un administrador con ese usuario,
	 *         {@code false} en caso contrario.
	 */
	boolean existsByUsuario(String usuario);
}
