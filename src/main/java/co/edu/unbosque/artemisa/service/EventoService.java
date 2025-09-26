package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.EventoDTO;
import co.edu.unbosque.artemisa.entity.Evento;
import co.edu.unbosque.artemisa.repository.EventoRepository;

/**
 * Servicio que implementa las operaciones CRUD y funcionalidades adicionales
 * para la gestión de {@link Evento}.
 * <p>
 * Utiliza {@link EventoRepository} para interactuar con la base de datos y
 * {@link ModelMapper} para mapear entre entidades y DTOs.
 * </p>
 */
@Service
public class EventoService implements CRUDOperation<EventoDTO> {

	@Autowired
	private EventoRepository eventoRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo evento en el sistema.
	 *
	 * @param data DTO con la información del evento a registrar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se creó exitosamente.</li>
	 *         <li>1 si ya existe un evento con el mismo título.</li>
	 *         </ul>
	 */
	@Override
	public int create(EventoDTO data) {
		Evento entity = modelMapper.map(data, Evento.class);
		if (findEventoAlreadyTaken(entity)) {
			return 1;
		} else {
			eventoRepo.save(entity);
			return 0;
		}
	}

	/**
	 * Obtiene todos los eventos registrados.
	 *
	 * @return lista de {@link EventoDTO}, o una lista vacía si no existen
	 *         registros.
	 */
	@Override
	public List<EventoDTO> getAll() {
		List<Evento> entityList = eventoRepo.findAll();
		List<EventoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EventoDTO dto = modelMapper.map(entity, EventoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Elimina un evento por su identificador.
	 *
	 * @param id identificador único del evento.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int deleteById(Long id) {
		return 0;
	}

	/**
	 * Actualiza un evento existente por su identificador.
	 *
	 * @param id      identificador del evento a actualizar.
	 * @param newData DTO con los nuevos datos del evento.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int updateById(Long id, EventoDTO newData) {
		return 0;
	}

	/**
	 * Cuenta la cantidad total de eventos registrados.
	 *
	 * @return número total de eventos (pendiente de implementación).
	 */
	@Override
	public long count() {
		return 0;
	}

	/**
	 * Verifica si un evento existe por su ID.
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
	 * Verifica si ya existe un evento con el mismo título.
	 *
	 * @param newEvento entidad evento con el título a validar.
	 * @return {@code true} si ya existe, {@code false} en caso contrario.
	 */
	public boolean findEventoAlreadyTaken(Evento newEvento) {
		Optional<Evento> found = eventoRepo.findByTitulo(newEvento.getTitulo());
		return found.isPresent();
	}

	/**
	 * Elimina un evento por su título.
	 *
	 * @param titulo título del evento a eliminar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se eliminó exitosamente.</li>
	 *         <li>1 si no existe un evento con ese título.</li>
	 *         </ul>
	 */
	public int deleteByTitle(String titulo) {
		Optional<Evento> found = eventoRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			eventoRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
