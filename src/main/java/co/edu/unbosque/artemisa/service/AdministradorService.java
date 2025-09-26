package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.AdministradorDTO;
import co.edu.unbosque.artemisa.entity.Administrador;
import co.edu.unbosque.artemisa.repository.AdministradorRepository;
import co.edu.unbosque.artemisa.util.AESUtil;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con la
 * entidad {@link Administrador}. Implementa la interfaz {@link CRUDOperation}
 * para exponer operaciones CRUD estándar y métodos adicionales como
 * autenticación e imagen de perfil.
 */
@Service
public class AdministradorService implements CRUDOperation<AdministradorDTO> {

	@Autowired
	private AdministradorRepository adminrepo;

	@Autowired
	private ModelMapper modelMapper;

	public AdministradorService() {
	}

	/**
	 * Crea un nuevo administrador en el sistema.
	 *
	 * @param data objeto {@link AdministradorDTO} con la información del
	 *             administrador.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 → creado exitosamente</li>
	 *         <li>1 → usuario ya existe</li>
	 *         <li>2 → datos inválidos</li>
	 *         </ul>
	 */
	@Override
	public int create(AdministradorDTO data) {
		try {
			if (data.getUsuario() == null || data.getUsuario().trim().isEmpty()) {
				return 2;
			}
			if (data.getContrasenia() == null || data.getContrasenia().trim().isEmpty()) {
				return 2;
			}

			Administrador entity = new Administrador();
			entity.setUsuario(AESUtil.encrypt(data.getUsuario()));
			entity.setContrasenia(AESUtil.encrypt(data.getContrasenia()));
			entity.setNivelDePermiso(AESUtil.encrypt(data.getNivelDePermiso()));

			if (data.getFechaDeNacimiento() != null && !data.getFechaDeNacimiento().trim().isEmpty()) {
				entity.setFechaDeNacimiento(AESUtil.encrypt(data.getFechaDeNacimiento()));
			} else {
				entity.setFechaDeNacimiento(AESUtil.encrypt(""));
			}

			if (findUsernameAlreadyTaken(entity)) {
				return 1;
			} else {
				adminrepo.save(entity);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * Autentica un administrador con usuario y contraseña.
	 *
	 * @param username usuario ingresado.
	 * @param password contraseña ingresada.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 → autenticación exitosa</li>
	 *         <li>1 → usuario no encontrado</li>
	 *         <li>2 → contraseña incorrecta</li>
	 *         <li>3 → error interno</li>
	 *         </ul>
	 */
	public int authenticateAdmin(String username, String password) {
		try {
			if (username == null || username.trim().isEmpty()) {
				return 1;
			}
			if (password == null || password.trim().isEmpty()) {
				return 2;
			}

			String encryptedUsername = AESUtil.encrypt(username);
			Optional<Administrador> adminFound = adminrepo.findByUsuario(encryptedUsername);

			if (adminFound.isEmpty()) {
				return 1;
			}

			Administrador admin = adminFound.get();
			String decryptedPassword = AESUtil.decrypt(admin.getContrasenia());

			if (password.equals(decryptedPassword)) {
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
	 * Obtiene todos los administradores registrados en la base de datos.
	 *
	 * @return lista de objetos {@link AdministradorDTO}.
	 */
	@Override
	public List<AdministradorDTO> getAll() {
		try {
			List<Administrador> entityList = adminrepo.findAll();
			List<AdministradorDTO> dtoList = new ArrayList<>();

			entityList.forEach((entity) -> {
				try {
					AdministradorDTO dto = new AdministradorDTO();
					dto.setId(entity.getId());
					dto.setUsuario(AESUtil.decrypt(entity.getUsuario()));
					dto.setContrasenia(AESUtil.decrypt(entity.getContrasenia()));
					dto.setNivelDePermiso(AESUtil.decrypt(entity.getNivelDePermiso()));

					if (entity.getFechaDeNacimiento() != null && !entity.getFechaDeNacimiento().trim().isEmpty()) {
						dto.setFechaDeNacimiento(AESUtil.decrypt(entity.getFechaDeNacimiento()));
					} else {
						dto.setFechaDeNacimiento("");
					}

					dtoList.add(dto);
				} catch (Exception e) {
				}
			});

			return dtoList;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	/**
	 * Elimina un administrador por su ID.
	 *
	 * @param id identificador del administrador.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 → eliminado exitosamente</li>
	 *         <li>1 → no encontrado</li>
	 *         <li>2 → error interno</li>
	 *         </ul>
	 */
	@Override
	public int deleteById(Long id) {
		try {
			if (adminrepo.existsById(id)) {
				adminrepo.deleteById(id);
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Actualiza un administrador por su ID.
	 *
	 * @param id      identificador del administrador.
	 * @param newData datos nuevos a actualizar.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 → actualizado exitosamente</li>
	 *         <li>1 → usuario ya existe</li>
	 *         <li>2 → no encontrado</li>
	 *         <li>3 → error interno</li>
	 *         </ul>
	 */
	@Override
	public int updateById(Long id, AdministradorDTO newData) {
		try {
			Optional<Administrador> found = adminrepo.findById(id);

			if (!found.isPresent()) {
				return 2;
			}

			String encryptedNewUsername = AESUtil.encrypt(newData.getUsuario());
			Optional<Administrador> existingUser = adminrepo.findByUsuario(encryptedNewUsername);

			if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
				return 1;
			}

			Administrador admin = found.get();
			admin.setUsuario(AESUtil.encrypt(newData.getUsuario()));
			admin.setContrasenia(AESUtil.encrypt(newData.getContrasenia()));
			admin.setNivelDePermiso(AESUtil.encrypt(newData.getNivelDePermiso()));

			if (newData.getFechaDeNacimiento() != null) {
				admin.setFechaDeNacimiento(AESUtil.encrypt(newData.getFechaDeNacimiento()));
			}

			adminrepo.save(admin);
			return 0;
		} catch (Exception e) {
			return 3;
		}
	}

	/**
	 * Cuenta el número total de administradores registrados.
	 *
	 * @return número de administradores.
	 */
	@Override
	public long count() {
		return adminrepo.count();
	}

	/**
	 * Verifica si existe un administrador por su ID.
	 *
	 * @param id identificador del administrador.
	 * @return {@code true} si existe, {@code false} en caso contrario.
	 */
	@Override
	public boolean exist(Long id) {
		return adminrepo.existsById(id);
	}

	/**
	 * Actualiza la imagen de perfil de un administrador.
	 *
	 * @param usuario      nombre de usuario.
	 * @param imagenBase64 imagen en formato Base64.
	 * @return un código de estado:
	 *         <ul>
	 *         <li>0 → imagen actualizada</li>
	 *         <li>1 → usuario no encontrado</li>
	 *         <li>2 → error interno</li>
	 *         </ul>
	 */
	public int actualizarImagen(String usuario, String imagenBase64) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Administrador> found = adminrepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				Administrador admin = found.get();
				admin.setImagenPerfil(imagenBase64);
				adminrepo.save(admin);
				return 0;
			}
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Obtiene la imagen de perfil de un administrador.
	 *
	 * @param usuario nombre de usuario.
	 * @return cadena en formato Base64 con la imagen, o {@code null} si no existe.
	 */
	public String obtenerImagen(String usuario) {
		try {
			String usuarioEncriptado = AESUtil.encrypt(usuario);
			Optional<Administrador> found = adminrepo.findByUsuario(usuarioEncriptado);

			if (found.isPresent()) {
				return found.get().getImagenPerfil();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Verifica si un nombre de usuario ya está en uso.
	 *
	 * @param newUser entidad {@link Administrador} a verificar.
	 * @return {@code true} si el usuario ya existe, {@code false} en caso
	 *         contrario.
	 */
	public boolean findUsernameAlreadyTaken(Administrador newUser) {
		try {
			Optional<Administrador> found = adminrepo.findByUsuario(newUser.getUsuario());
			return found.isPresent();
		} catch (Exception e) {
			return false;
		}
	}
}
