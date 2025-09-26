package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Profesor;

/**
 * Repositorio JPA para la entidad {@link Profesor}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas sobre el campo {@code usuario}.
 * </p>
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	/**
	 * Busca un profesor por su usuario.
	 *
	 * @param usuario nombre de usuario del profesor a buscar.
	 * @return un {@link Optional} que contiene el profesor si existe, o vacío si no
	 *         se encuentra.
	 */
	Optional<Profesor> findByUsuario(String usuario);

	/**
	 * Elimina un profesor por su usuario.
	 *
	 * @param usuario nombre de usuario del profesor a eliminar.
	 */
	void deleteByUsuario(String usuario);

	/**
	 * Verifica si existe un profesor con el usuario especificado.
	 *
	 * @param usuario nombre de usuario a verificar.
	 * @return {@code true} si existe un profesor con ese usuario, {@code false} en
	 *         caso contrario.
	 */
	boolean existsByUsuario(String usuario);
}
