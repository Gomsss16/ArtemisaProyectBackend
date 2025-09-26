package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.entity.Temario;
import co.edu.unbosque.artemisa.repository.TemarioRepository;

@Service
public class TemarioService implements CRUDOperation<TemarioDTO> {

	@Autowired
	private TemarioRepository temaRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo temario en la base de datos.
	 *
	 * @param data Objeto {@link TemarioDTO} con la información del temario.
	 * @return 0 si se creó exitosamente, 1 si ya existe un temario con el mismo
	 *         nombre de algoritmo.
	 */
	@Override
	public int create(TemarioDTO data) {
		Temario entity = modelMapper.map(data, Temario.class);
		if (findtemaAlreadyTaken(entity)) {
			return 1;
		} else {
			temaRepo.save(entity);
			return 0;
		}
	}

	/**
	 * Obtiene todos los temarios registrados en la base de datos.
	 *
	 * @return Lista de {@link TemarioDTO}.
	 */
	@Override
	public List<TemarioDTO> getAll() {
		List<Temario> entityList = temaRepo.findAll();
		List<TemarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			TemarioDTO dto = modelMapper.map(entity, TemarioDTO.class);
			dtoList.add(dto);
		});

		return dtoList;
	}

	/**
	 * Elimina un temario por ID.
	 *
	 * @param id Identificador único del temario.
	 * @return 0 si se eliminó, 1 si no existe el temario, 2 si ocurrió un error
	 *         (pendiente implementar).
	 */
	@Override
	public int deleteById(Long id) {
		// TODO implementar lógica de eliminación
		return 0;
	}

	/**
	 * Actualiza un temario por su ID.
	 *
	 * @param id      Identificador único del temario.
	 * @param newData Objeto {@link TemarioDTO} con la nueva información.
	 * @return 0 si se actualizó correctamente, 1 si ya existe otro temario con el
	 *         mismo nombre, 2 si no existe el temario, 3 si ocurrió un error
	 *         (pendiente implementar).
	 */
	@Override
	public int updateById(Long id, TemarioDTO newData) {
		// TODO implementar lógica de actualización
		return 0;
	}

	/**
	 * Cuenta la cantidad de temarios registrados.
	 *
	 * @return Número total de registros (pendiente implementar).
	 */
	@Override
	public long count() {
		// TODO implementar lógica de conteo
		return 0;
	}

	/**
	 * Verifica si un temario existe por su ID.
	 *
	 * @param id Identificador único del temario.
	 * @return {@code true} si existe, {@code false} en caso contrario (pendiente
	 *         implementar).
	 */
	@Override
	public boolean exist(Long id) {
		// TODO implementar lógica de verificación
		return false;
	}

	/**
	 * Verifica si ya existe un temario con el mismo nombre de algoritmo.
	 *
	 * @param newUser Objeto {@link Temario} con el tema a verificar.
	 * @return {@code true} si ya existe, {@code false} si no.
	 */
	public boolean findtemaAlreadyTaken(Temario newUser) {
		Optional<Temario> found = temaRepo.findByTemaAlgoritmo(newUser.getTemaAlgoritmo());
		return found.isPresent();
	}

	/**
	 * Obtiene todos los temarios filtrados por tipo.
	 *
	 * @param tipo Tipo de temario (ejemplo: "Teoría", "Ejercicios").
	 * @return Lista de {@link TemarioDTO} que coinciden con el tipo.
	 */
	public List<TemarioDTO> getByTipo(String tipo) {
		List<Temario> entities = temaRepo.findByTipo(tipo);

		return entities.stream()
				.map(e -> new TemarioDTO(e.getId(), e.getTemaAlgoritmo(), e.getTipo(), e.getContenido(), e.getCodigo()))
				.toList();
	}

	/**
	 * Elimina un temario por su nombre de algoritmo.
	 *
	 * @param tema Nombre del tema de algoritmo.
	 * @return 0 si se eliminó correctamente, 1 si no existe.
	 */
	public int deleteByTema(String tema) {
		Optional<Temario> found = temaRepo.findByTemaAlgoritmo(tema);
		if (found.isPresent()) {
			temaRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
