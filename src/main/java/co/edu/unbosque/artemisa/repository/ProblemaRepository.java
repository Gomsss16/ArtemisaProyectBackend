package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Problema;

/**
 * Repositorio JPA para la entidad {@link Problema}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas por el campo {@code titulo}.
 * </p>
 */
public interface ProblemaRepository extends JpaRepository<Problema, Long> {

	/**
	 * Busca un problema por su título.
	 *
	 * @param titulo título del problema a buscar.
	 * @return un {@link Optional} que contiene el problema si existe, o vacío si no
	 *         se encuentra.
	 */
	Optional<Problema> findByTitulo(String titulo);

	/**
	 * Verifica si existe un problema con el título especificado.
	 *
	 * @param titulo título del problema a verificar.
	 * @return {@code true} si existe un problema con ese título, {@code false} en
	 *         caso contrario.
	 */
	boolean existsByTitulo(String titulo);
}
