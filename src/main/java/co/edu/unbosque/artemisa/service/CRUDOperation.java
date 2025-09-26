package co.edu.unbosque.artemisa.service;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones básicas CRUD (Crear, Leer,
 * Actualizar y Eliminar) para una entidad representada en forma de DTO (Data
 * Transfer Object).
 *
 * @param <D> tipo de DTO sobre el que se aplicarán las operaciones.
 */
public interface CRUDOperation<D> {

	/**
	 * Crea un nuevo registro a partir de un DTO.
	 *
	 * @param data objeto DTO con la información a persistir.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 si la creación fue exitosa.</li>
	 *         <li>1 si el registro ya existe.</li>
	 *         <li>2 si hubo error de validación o datos inválidos.</li>
	 *         <li>3 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	int create(D data);

	/**
	 * Obtiene todos los registros disponibles.
	 *
	 * @return una lista de objetos DTO. Si no existen registros, se devuelve una
	 *         lista vacía.
	 */
	List<D> getAll();

	/**
	 * Elimina un registro según su identificador único.
	 *
	 * @param id identificador del registro a eliminar.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 si la eliminación fue exitosa.</li>
	 *         <li>1 si el registro no existe.</li>
	 *         <li>2 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	int deleteById(Long id);

	/**
	 * Actualiza un registro existente por su identificador.
	 *
	 * @param id      identificador del registro a actualizar.
	 * @param newData DTO con los nuevos datos.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 si la actualización fue exitosa.</li>
	 *         <li>1 si el nuevo dato entra en conflicto (ej: duplicado).</li>
	 *         <li>2 si el registro no existe.</li>
	 *         <li>3 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	int updateById(Long id, D newData);

	/**
	 * Cuenta la cantidad total de registros disponibles.
	 *
	 * @return el número total de registros.
	 */
	long count();

	/**
	 * Verifica si existe un registro por su identificador.
	 *
	 * @param id identificador del registro a verificar.
	 * @return {@code true} si el registro existe, {@code false} en caso contrario.
	 */
	boolean exist(Long id);
}
