package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.ProfesorDTO;
import co.edu.unbosque.artemisa.entity.Profesor;
import co.edu.unbosque.artemisa.repository.ProfesorRepository;
import co.edu.unbosque.artemisa.util.AESUtil;

@Service
public class ProfesorService implements CRUDOperation<ProfesorDTO> {

	@Autowired
	private ProfesorRepository proferepo;

	@Autowired
	private ModelMapper modelMapper;

	public ProfesorService() {
	}

	/**
	 * Crea un nuevo profesor en la base de datos.
	 *
	 * @param data Objeto {@link ProfesorDTO} con la información del profesor.
	 * @return 0 si se creó exitosamente, 1 si el usuario ya existe, 2 si los datos
	 *         son inválidos o hubo un error.
	 */
	@Override
	public int create(ProfesorDTO data) {
		try {
			if (data.getUsuario() == null || data.getUsuario().trim().isEmpty()) {
				return 2;
			}

			if (data.getContrasenia() == null || data.getContrasenia().trim().isEmpty()) {
				return 2;
			}

			Profesor entity = modelMapper.map(data, Profesor.class);

			if (findUsernameAlreadyTaken(entity)) {
				return 1;
			} else {
				proferepo.save(entity);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * Verifica las credenciales de un profesor.
	 *
	 * @param username Usuario del profesor.
	 * @param password Contraseña del profesor.
	 * @return 0 si autenticación es exitosa, 1 si el usuario no existe, 2 si la
	 *         contraseña es incorrecta, 3 si ocurre un error inesperado.
	 */
	public int authenticateProfesor(String username, String password) {
		try {
			if (username == null || username.trim().isEmpty()) {
				return 1;
			}

			if (password == null || password.trim().isEmpty()) {
				return 2;
			}

			Optional<Profesor> profesorFound = proferepo.findByUsuario(username);

			if (profesorFound.isEmpty()) {
				return 1;
			}

			Profesor profesor = profesorFound.get();
			String storedPassword = profesor.getContrasenia();

			return password.equals(storedPassword) ? 0 : 2;

		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}

	/**
	 * Obtiene todos los profesores registrados.
	 *
	 * @return Lista de {@link ProfesorDTO}.
	 */
	@Override
	public List<ProfesorDTO> getAll() {
		try {
			List<Profesor> entityList = proferepo.findAll();

			List<ProfesorDTO> dtoList = new ArrayList<>();
			entityList.forEach((entity) -> {
				ProfesorDTO dto = modelMapper.map(entity, ProfesorDTO.class);
				dtoList.add(dto);
			});

			return dtoList;

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	/**
	 * Elimina un profesor por ID.
	 *
	 * @param id Identificador único del profesor.
	 * @return 0 si se eliminó, 1 si no se encontró, 2 si hubo un error.
	 */
	@Override
	public int deleteById(Long id) {
		try {
			if (proferepo.existsById(id)) {
				proferepo.deleteById(id);
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Actualiza la información de un profesor por ID.
	 *
	 * @param id      Identificador único del profesor.
	 * @param newData Objeto {@link ProfesorDTO} con la nueva información.
	 * @return 0 si se actualizó correctamente, 1 si el nuevo usuario ya existe, 2
	 *         si no se encontró el profesor, 3 si ocurrió un error inesperado.
	 */
	@Override
	public int updateById(Long id, ProfesorDTO newData) {
		try {
			Optional<Profesor> found = proferepo.findById(id);

			if (!found.isPresent()) {
				return 2;
			}

			Optional<Profesor> existingUser = proferepo.findByUsuario(newData.getUsuario());

			if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
				return 1;
			}

			Profesor profesor = found.get();
			profesor.setUsuario(newData.getUsuario());
			profesor.setContrasenia(newData.getContrasenia());
			profesor.setNivelDePermiso(newData.getNivelDePermiso());

			if (newData.getFechaDeNacimiento() != null) {
				profesor.setFechaDeNacimiento(newData.getFechaDeNacimiento());
			}

			proferepo.save(profesor);
			return 0;

		} catch (Exception e) {
			return 3;
		}
	}

	/**
	 * Actualiza la imagen de perfil de un profesor.
	 *
	 * @param usuario      Usuario del profesor.
	 * @param imagenBase64 Imagen en formato Base64.
	 * @return 0 si se actualizó correctamente, 1 si no se encontró el profesor, 2
	 *         si ocurrió un error.
	 */
	public int actualizarImagen(String usuario, String imagenBase64) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Profesor> found = proferepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				Profesor admin = found.get();
				admin.setImagenPerfil(imagenBase64);
				proferepo.save(admin);
				return 0;
			}
			return 1;

		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Obtiene la imagen de perfil de un profesor.
	 *
	 * @param usuario Usuario del profesor.
	 * @return Cadena Base64 de la imagen, o {@code null} si no existe.
	 */
	public String obtenerImagen(String usuario) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Profesor> found = proferepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				return found.get().getImagenPerfil();
			}
			return null;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Cuenta la cantidad de profesores registrados.
	 *
	 * @return Número total de profesores.
	 */
	@Override
	public long count() {
		return proferepo.count();
	}

	/**
	 * Verifica si un profesor existe por ID.
	 *
	 * @param id Identificador único del profesor.
	 * @return {@code true} si existe, {@code false} en caso contrario.
	 */
	@Override
	public boolean exist(Long id) {
		return proferepo.existsById(id);
	}

	/**
	 * Verifica si un nombre de usuario ya está registrado.
	 *
	 * @param newUser Objeto {@link Profesor} con el usuario a validar.
	 * @return {@code true} si ya existe, {@code false} en caso contrario.
	 */
	public boolean findUsernameAlreadyTaken(Profesor newUser) {
		try {
			Optional<Profesor> found = proferepo.findByUsuario(newUser.getUsuario());
			return found.isPresent();
		} catch (Exception e) {
			return false;
		}
	}
}
