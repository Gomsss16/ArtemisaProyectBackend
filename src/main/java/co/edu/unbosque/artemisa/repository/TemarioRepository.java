package co.edu.unbosque.artemisa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Temario;

/**
 * Repositorio JPA para la entidad {@link Temario}.
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar,
 * además de consultas personalizadas por los campos {@code temaAlgoritmo} y {@code tipo}.
 * </p>
 */
public interface TemarioRepository extends JpaRepository<Temario, Long> {

    /**
     * Busca un temario por su tema de algoritmo.
     *
     * @param temaAlgoritmo tema del algoritmo a buscar.
     * @return un {@link Optional} que contiene el temario si existe,
     *         o vacío si no se encuentra.
     */
    Optional<Temario> findByTemaAlgoritmo(String temaAlgoritmo);

    /**
     * Elimina un temario por su tema de algoritmo.
     *
     * @param temaAlgoritmo tema del algoritmo del temario a eliminar.
     */
    void deleteByTemaAlgoritmo(String temaAlgoritmo);

    /**
     * Verifica si existe un temario con el tema de algoritmo especificado.
     *
     * @param temaAlgoritmo tema del algoritmo a verificar.
     * @return {@code true} si existe un temario con ese tema,
     *         {@code false} en caso contrario.
     */
    boolean existsByTemaAlgoritmo(String temaAlgoritmo);

    /**
     * Busca todos los temarios filtrados por tipo.
     *
     * @param tipo tipo de temario a buscar (ejemplo: "teórico", "práctico").
     * @return una lista de temarios que coinciden con el tipo,
     *         o una lista vacía si no se encuentran resultados.
     */
    List<Temario> findByTipo(String tipo);
}
