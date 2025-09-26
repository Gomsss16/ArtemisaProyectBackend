package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.artemisa.entity.Evento;

/**
 * Repositorio JPA para la entidad {@link Evento}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas por el campo {@code titulo}.
 * </p>
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	/**
	 * Busca un evento por su título.
	 *
	 * @param titulo título del evento a buscar.
	 * @return un {@link Optional} que contiene el evento si existe, o vacío si no
	 *         se encuentra.
	 */
	Optional<Evento> findByTitulo(String titulo);
}
