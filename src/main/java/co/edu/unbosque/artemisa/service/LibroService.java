package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.entity.Libro;
import co.edu.unbosque.artemisa.repository.LibroRepository;
import java.util.Base64;

/**
 * Servicio encargado de gestionar las operaciones CRUD para la entidad
 * {@link Libro}.
 * <p>
 * Se encarga de convertir entre {@link LibroDTO} y {@link Libro}, manejar
 * archivos binarios (PDF e imágenes) en formato Base64 y delegar operaciones de
 * persistencia al repositorio {@link LibroRepository}.
 * </p>
 */
@Service
public class LibroService implements CRUDOperation<LibroDTO> {

	@Autowired
	private LibroRepository libroRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo libro en el sistema.
	 *
	 * @param data DTO con la información del libro a registrar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se creó exitosamente.</li>
	 *         <li>1 si ya existe un libro con el mismo título.</li>
	 *         </ul>
	 */
	@Override
	public int create(LibroDTO data) {
		Libro entity = new Libro();
		entity.setTitulo(data.getTitulo());
		entity.setAuthor(data.getAuthor());
		entity.setDescripcion(data.getDescripcion());
		entity.setEnlace(data.getEnlace());

		if (data.getPdfBase64() != null && !data.getPdfBase64().isEmpty()) {
			entity.setPdf(Base64.getDecoder().decode(data.getPdfBase64()));
		}

		if (data.getImagenBase64() != null && !data.getImagenBase64().isEmpty()) {
			entity.setImagen(Base64.getDecoder().decode(data.getImagenBase64()));
		}

		if (findlibroAlreadyTaken(entity)) {
			return 1;
		}

		libroRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los libros registrados en el sistema. Convierte los archivos
	 * binarios (PDF e imágenes) a cadenas en Base64.
	 *
	 * @return lista de {@link LibroDTO}, o una lista vacía si no existen registros.
	 */
	@Override
	public List<LibroDTO> getAll() {
		List<Libro> entityList = libroRepo.findAll();
		List<LibroDTO> dtoList = new ArrayList<>();

		entityList.forEach((entity) -> {
			LibroDTO dto = new LibroDTO();
			dto.setId(entity.getId());
			dto.setTitulo(entity.getTitulo());
			dto.setAuthor(entity.getAuthor());
			dto.setDescripcion(entity.getDescripcion());
			dto.setEnlace(entity.getEnlace());

			if (entity.getPdf() != null) {
				dto.setPdfBase64(Base64.getEncoder().encodeToString(entity.getPdf()));
			}

			if (entity.getImagen() != null) {
				dto.setImagenBase64(Base64.getEncoder().encodeToString(entity.getImagen()));
			}

			dtoList.add(dto);
		});

		return dtoList;
	}

	/**
	 * Elimina un libro por su identificador.
	 *
	 * @param id identificador único del libro.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int deleteById(Long id) {
		return 0;
	}

	/**
	 * Actualiza un libro existente por su identificador.
	 *
	 * @param id      identificador del libro a actualizar.
	 * @param newData DTO con los nuevos datos.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int updateById(Long id, LibroDTO newData) {
		return 0;
	}

	/**
	 * Cuenta la cantidad total de libros registrados.
	 *
	 * @return número total de libros (pendiente de implementación).
	 */
	@Override
	public long count() {
		return 0;
	}

	/**
	 * Verifica si un libro existe por su ID.
	 *
	 * @param id identificador único.
	 * @return {@code true} si existe, {@code false} en caso contrario (pendiente de
	 *         implementación).
	 */
	@Override
	public boolean exist(Long id) {
		return false;
	}

	/**
	 * Verifica si ya existe un libro con el mismo título.
	 *
	 * @param newLibro entidad libro con el título a validar.
	 * @return {@code true} si ya existe, {@code false} en caso contrario.
	 */
	public boolean findlibroAlreadyTaken(Libro newLibro) {
		Optional<Libro> found = libroRepo.findByTitulo(newLibro.getTitulo());
		return found.isPresent();
	}

	/**
	 * Elimina un libro por su título.
	 *
	 * @param titulo título del libro a eliminar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se eliminó exitosamente.</li>
	 *         <li>1 si no existe un libro con ese título.</li>
	 *         </ul>
	 */
	public int deleteByTitle(String titulo) {
		Optional<Libro> found = libroRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			libroRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
