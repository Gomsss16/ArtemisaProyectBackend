package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Estudiante;

/**
 * Repositorio JPA para la entidad {@link Estudiante}.
 * <p>
 * Proporciona operaciones CRUD heredadas de {@link JpaRepository}, además de
 * consultas personalizadas basadas en el campo {@code usuario}.
 * </p>
 */
public interface EstudiateRepository extends JpaRepository<Estudiante, Long> {

	/**
	 * Busca un estudiante por su nombre de usuario.
	 *
	 * @param usuario nombre de usuario del estudiante a buscar.
	 * @return un {@link Optional} que contiene el estudiante si se encuentra, o
	 *         vacío si no existe.
	 */
	Optional<Estudiante> findByUsuario(String usuario);

	/**
	 * Elimina un estudiante de la base de datos según su nombre de usuario.
	 *
	 * @param usuario nombre de usuario del estudiante a eliminar.
	 */
	void deleteByUsuario(String usuario);

	/**
	 * Verifica si existe un estudiante con un nombre de usuario específico.
	 *
	 * @param usuario nombre de usuario a verificar.
	 * @return {@code true} si existe un estudiante con ese usuario, {@code false}
	 *         en caso contrario.
	 */
	boolean existsByUsuario(String usuario);
}
