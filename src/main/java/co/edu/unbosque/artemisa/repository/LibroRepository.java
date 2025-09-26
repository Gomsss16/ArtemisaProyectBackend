package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Libro;

/**
 * Repositorio JPA para la entidad {@link Libro}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas por el campo {@code titulo}.
 * </p>
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {

	/**
	 * Busca un libro por su título.
	 *
	 * @param titulo título del libro a buscar.
	 * @return un {@link Optional} que contiene el libro si existe, o vacío si no se
	 *         encuentra.
	 */
	Optional<Libro> findByTitulo(String titulo);

	/**
	 * Verifica si existe un libro con el título especificado.
	 *
	 * @param titulo título del libro a verificar.
	 * @return {@code true} si existe un libro con ese título, {@code false} en caso
	 *         contrario.
	 */
	boolean existsByTitulo(String titulo);
}
