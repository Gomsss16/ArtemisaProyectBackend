package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Link;

/**
 * Repositorio JPA para la entidad {@link Link}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas por el campo {@code titulo}.
 * </p>
 */
public interface LinkRepository extends JpaRepository<Link, Long> {

	/**
	 * Busca un link por su título.
	 *
	 * @param titulo título del link a buscar.
	 * @return un {@link Optional} que contiene el link si existe, o vacío si no se
	 *         encuentra.
	 */
	Optional<Link> findByTitulo(String titulo);

	/**
	 * Verifica si existe un link con el título especificado.
	 *
	 * @param titulo título del link a verificar.
	 * @return {@code true} si existe un link con ese título, {@code false} en caso
	 *         contrario.
	 */
	boolean existsByTitulo(String titulo);
}
