package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.ProblemaDTO;
import co.edu.unbosque.artemisa.entity.Problema;
import co.edu.unbosque.artemisa.repository.ProblemaRepository;

/**
 * Servicio encargado de gestionar las operaciones CRUD para la entidad
 * {@link Problema}.
 * <p>
 * Utiliza {@link ModelMapper} para la conversión entre entidades y DTOs, y
 * delega la persistencia al repositorio {@link ProblemaRepository}.
 * </p>
 */
@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO> {

	@Autowired
	private ProblemaRepository probleRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo problema en el sistema.
	 *
	 * @param data DTO con la información del problema a registrar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se creó exitosamente.</li>
	 *         <li>1 si ya existe un problema con el mismo título.</li>
	 *         </ul>
	 */
	@Override
	public int create(ProblemaDTO data) {
		Problema entity = modelMapper.map(data, Problema.class);
		if (findTituloAlreadyTaken(entity)) {
			return 1;
		} else {
			probleRepo.save(entity);
			return 0;
		}
	}

	/**
	 * Obtiene todos los problemas registrados en el sistema.
	 *
	 * @return lista de {@link ProblemaDTO}, o una lista vacía si no existen
	 *         registros.
	 */
	@Override
	public List<ProblemaDTO> getAll() {
		List<Problema> entityList = probleRepo.findAll();
		List<ProblemaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ProblemaDTO dto = modelMapper.map(entity, ProblemaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Elimina un problema por su identificador único.
	 *
	 * @param id identificador único del problema.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int deleteById(Long id) {
		return 0;
	}

	/**
	 * Actualiza un problema existente por su identificador.
	 *
	 * @param id      identificador del problema a actualizar.
	 * @param newData DTO con los nuevos datos.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int updateById(Long id, ProblemaDTO newData) {
		return 0;
	}

	/**
	 * Cuenta la cantidad total de problemas registrados en el sistema.
	 *
	 * @return número total de problemas almacenados (pendiente de implementación).
	 */
	@Override
	public long count() {
		return 0;
	}

	/**
	 * Verifica si existe un problema por su identificador único.
	 *
	 * @param id identificador del problema.
	 * @return {@code true} si existe, {@code false} en caso contrario.
	 */
	@Override
	public boolean exist(Long id) {
		return false;
	}

	/**
	 * Verifica si ya existe un problema con el mismo título.
	 *
	 * @param newUser entidad problema con el título a validar.
	 * @return {@code true} si ya existe, {@code false} en caso contrario.
	 */
	public boolean findTituloAlreadyTaken(Problema newUser) {
		Optional<Problema> found = probleRepo.findByTitulo(newUser.getTitulo());
		return found.isPresent();
	}

	/**
	 * Elimina un problema por su título.
	 *
	 * @param titulo título del problema a eliminar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se eliminó exitosamente.</li>
	 *         <li>1 si no existe un problema con ese título.</li>
	 *         </ul>
	 */
	public int deleteByTema(String titulo) {
		Optional<Problema> found = probleRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			probleRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
