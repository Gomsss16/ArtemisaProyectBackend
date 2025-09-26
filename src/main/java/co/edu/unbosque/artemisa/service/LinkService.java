package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LinkDTO;
import co.edu.unbosque.artemisa.entity.Link;
import co.edu.unbosque.artemisa.repository.LinkRepository;

/**
 * Servicio encargado de gestionar las operaciones CRUD para la entidad
 * {@link Link}.
 * <p>
 * Se encarga de transformar entre {@link LinkDTO} y {@link Link}, gestionar
 * archivos binarios de imágenes en formato Base64 y delegar la persistencia al
 * repositorio {@link LinkRepository}.
 * </p>
 */
@Service
public class LinkService implements CRUDOperation<LinkDTO> {

	@Autowired
	private LinkRepository linkRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo link en el sistema.
	 *
	 * @param data DTO con la información del link a registrar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se creó exitosamente.</li>
	 *         <li>1 si ya existe un link con el mismo título.</li>
	 *         <li>2 si ocurrió un error durante la operación.</li>
	 *         </ul>
	 */
	@Override
	public int create(LinkDTO data) {
		try {
			Link entity = new Link();
			entity.setTitulo(data.getTitulo());
			entity.setDescripcion(data.getDescripcion());
			entity.setEnlace(data.getEnlace());

			if (data.getImagenBase64() != null && !data.getImagenBase64().isEmpty()) {
				String base64Data = data.getImagenBase64();
				if (base64Data.contains(",")) {
					base64Data = base64Data.split(",")[1];
				}
				entity.setImagen(Base64.getDecoder().decode(base64Data));
			}

			if (findLinkAlreadyTaken(entity)) {
				return 1;
			}

			linkRepo.save(entity);
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * Obtiene todos los links registrados en el sistema. Convierte las imágenes
	 * almacenadas en binario a cadenas en Base64.
	 *
	 * @return lista de {@link LinkDTO}, o una lista vacía si no existen registros.
	 */
	@Override
	public List<LinkDTO> getAll() {
		List<Link> entityList = linkRepo.findAll();
		List<LinkDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			LinkDTO dto = modelMapper.map(entity, LinkDTO.class);

			if (entity.getImagen() != null) {
				String base64 = Base64.getEncoder().encodeToString(entity.getImagen());
				dto.setImagenBase64("data:image/png;base64," + base64);
			}

			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Elimina un link por su identificador único.
	 *
	 * @param id identificador único del link.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int deleteById(Long id) {
		return 0;
	}

	/**
	 * Actualiza un link existente por su identificador.
	 *
	 * @param id      identificador del link a actualizar.
	 * @param newData DTO con los nuevos datos.
	 * @return código de estado (pendiente de implementación).
	 */
	@Override
	public int updateById(Long id, LinkDTO newData) {
		return 0;
	}

	/**
	 * Cuenta la cantidad total de links registrados en el sistema.
	 *
	 * @return número total de links almacenados.
	 */
	@Override
	public long count() {
		return linkRepo.count();
	}

	/**
	 * Verifica si existe un link por su identificador único.
	 *
	 * @param id identificador del link.
	 * @return {@code true} si existe, {@code false} en caso contrario.
	 */
	@Override
	public boolean exist(Long id) {
		return linkRepo.existsById(id);
	}

	/**
	 * Verifica si ya existe un link con el mismo título.
	 *
	 * @param newLink entidad link con el título a validar.
	 * @return {@code true} si ya existe, {@code false} en caso contrario.
	 */
	public boolean findLinkAlreadyTaken(Link newLink) {
		Optional<Link> found = linkRepo.findByTitulo(newLink.getTitulo());
		return found.isPresent();
	}

	/**
	 * Elimina un link por su título.
	 *
	 * @param titulo título del link a eliminar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se eliminó exitosamente.</li>
	 *         <li>1 si no existe un link con ese título.</li>
	 *         </ul>
	 */
	public int deleteByTitle(String titulo) {
		Optional<Link> found = linkRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			linkRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
