package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.EstudianteDTO;
import co.edu.unbosque.artemisa.entity.Estudiante;
import co.edu.unbosque.artemisa.repository.EstudiateRepository;
import co.edu.unbosque.artemisa.util.AESUtil;

/**
 * Servicio que implementa las operaciones CRUD y funcionalidades adicionales
 * para la gestión de {@link Estudiante}.
 * <p>
 * Utiliza {@link EstudiateRepository} para interactuar con la base de datos y
 * {@link ModelMapper} para transformar entidades en DTOs y viceversa.
 * </p>
 */
@Service
public class EstudianteService implements CRUDOperation<EstudianteDTO> {

	@Autowired
	private EstudiateRepository estudianterepo;

	@Autowired
	private ModelMapper modelMapper;

	public EstudianteService() {
	}

	/**
	 * Crea un nuevo estudiante a partir de un DTO.
	 *
	 * @param data DTO con la información del estudiante a registrar.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se creó exitosamente.</li>
	 *         <li>1 si el usuario ya existe.</li>
	 *         <li>2 si faltan datos obligatorios o ocurrió un error de
	 *         validación.</li>
	 *         </ul>
	 */
	@Override
	public int create(EstudianteDTO data) {
		try {
			if (data.getUsuario() == null || data.getUsuario().trim().isEmpty()) {
				return 2;
			}
			if (data.getContrasenia() == null || data.getContrasenia().trim().isEmpty()) {
				return 2;
			}

			Estudiante entity = modelMapper.map(data, Estudiante.class);

			if (findUsernameAlreadyTaken(entity)) {
				return 1;
			} else {
				estudianterepo.save(entity);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * Autentica un estudiante verificando sus credenciales.
	 *
	 * @param username nombre de usuario ingresado.
	 * @param password contraseña ingresada.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si la autenticación fue exitosa.</li>
	 *         <li>1 si el usuario no existe.</li>
	 *         <li>2 si la contraseña es incorrecta o está vacía.</li>
	 *         <li>3 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	public int authenticateEstudiante(String username, String password) {
		try {
			if (username == null || username.trim().isEmpty()) {
				return 1;
			}

			if (password == null || password.trim().isEmpty()) {
				return 2;
			}

			Optional<Estudiante> estudianteFound = estudianterepo.findByUsuario(username);

			if (estudianteFound.isEmpty()) {
				return 1;
			}

			Estudiante estudiante = estudianteFound.get();
			String storedPassword = estudiante.getContrasenia();

			if (password.equals(storedPassword)) {
				return 0;
			} else {
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}

	/**
	 * Obtiene todos los estudiantes registrados.
	 *
	 * @return lista de {@link EstudianteDTO}, o una lista vacía si no existen
	 *         registros.
	 */
	@Override
	public List<EstudianteDTO> getAll() {
		try {
			List<Estudiante> entityList = estudianterepo.findAll();
			List<EstudianteDTO> dtoList = new ArrayList<>();
			entityList.forEach((entity) -> {
				EstudianteDTO dto = modelMapper.map(entity, EstudianteDTO.class);
				dtoList.add(dto);
			});

			return dtoList;

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	/**
	 * Elimina un estudiante por su identificador.
	 *
	 * @param id identificador único del estudiante.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si fue eliminado exitosamente.</li>
	 *         <li>1 si no existe un estudiante con ese ID.</li>
	 *         <li>2 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	@Override
	public int deleteById(Long id) {
		try {
			if (estudianterepo.existsById(id)) {
				estudianterepo.deleteById(id);
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Actualiza la información de un estudiante existente.
	 *
	 * @param id      identificador del estudiante a actualizar.
	 * @param newData DTO con los nuevos datos.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si se actualizó exitosamente.</li>
	 *         <li>1 si el nuevo usuario ya existe en otro registro.</li>
	 *         <li>2 si el estudiante no existe.</li>
	 *         <li>3 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		try {
			Optional<Estudiante> found = estudianterepo.findById(id);

			if (!found.isPresent()) {
				return 2;
			}

			Optional<Estudiante> existingUser = estudianterepo.findByUsuario(newData.getUsuario());

			if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
				return 1;
			}

			Estudiante estudiante = found.get();
			estudiante.setUsuario(newData.getUsuario());
			estudiante.setContrasenia(newData.getContrasenia());
			estudiante.setNivelDePermiso(newData.getNivelDePermiso());

			if (newData.getFechaDeNacimiento() != null) {
				estudiante.setFechaDeNacimiento(newData.getFechaDeNacimiento());
			}

			estudianterepo.save(estudiante);
			return 0;

		} catch (Exception e) {
			return 3;
		}
	}

	/**
	 * Cuenta la cantidad total de estudiantes registrados.
	 *
	 * @return número total de estudiantes.
	 */
	@Override
	public long count() {
		return estudianterepo.count();
	}

	/**
	 * Actualiza la imagen de perfil de un estudiante.
	 *
	 * @param usuario      nombre de usuario del estudiante.
	 * @param imagenBase64 imagen codificada en Base64.
	 * @return código de estado:
	 *         <ul>
	 *         <li>0 si la actualización fue exitosa.</li>
	 *         <li>1 si el estudiante no existe.</li>
	 *         <li>2 si ocurrió un error inesperado.</li>
	 *         </ul>
	 */
	public int actualizarImagen(String usuario, String imagenBase64) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Estudiante> found = estudianterepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				Estudiante admin = found.get();
				admin.setImagenPerfil(imagenBase64);
				estudianterepo.save(admin);
				return 0;
			}
			return 1;

		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Obtiene la imagen de perfil de un estudiante.
	 *
	 * @param usuario nombre de usuario del estudiante.
	 * @return la imagen en Base64, o {@code null} si no existe.
	 */
	public String obtenerImagen(String usuario) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Estudiante> found = estudianterepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				return found.get().getImagenPerfil();
			}
			return null;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Verifica si un estudiante existe por su ID.
	 *
	 * @param id identificador único.
	 * @return {@code true} si existe, {@code false} en caso contrario.
	 */
	@Override
	public boolean exist(Long id) {
		return estudianterepo.existsById(id);
	}

	/**
	 * Verifica si un nombre de usuario ya está registrado.
	 *
	 * @param newUser entidad estudiante con el usuario a verificar.
	 * @return {@code true} si el usuario ya existe, {@code false} en caso
	 *         contrario.
	 */
	public boolean findUsernameAlreadyTaken(Estudiante newUser) {
		try {
			Optional<Estudiante> found = estudianterepo.findByUsuario(newUser.getUsuario());
			boolean exists = found.isPresent();
			return exists;
		} catch (Exception e) {
			return false;
		}
	}
}
