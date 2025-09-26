package co.edu.unbosque.artemisa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.artemisa.dto.ProfesorDTO;
import co.edu.unbosque.artemisa.service.ProfesorService;

/**
 * Controlador REST para la gestión de profesores.
 * 
 * Proporciona endpoints para:
 * <ul>
 * <li>Crear profesores (JSON o parámetros).</li>
 * <li>Iniciar sesión como profesor.</li>
 * <li>Obtener todos los profesores.</li>
 * <li>Actualizar u obtener imágenes de perfil en Base64.</li>
 * <li>Probar la disponibilidad del endpoint.</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/profesor" })
public class ProfesorController {

	@Autowired
	private ProfesorService profeser;

	public ProfesorController() {
	}

	/**
	 * Crea un nuevo profesor a partir de un objeto JSON.
	 *
	 * @param newUser objeto {@link ProfesorDTO} con la información del profesor.
	 * @return ResponseEntity con: - 201 (CREATED) si fue creado exitosamente. - 409
	 *         (CONFLICT) si el usuario ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createprofesorjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody ProfesorDTO newUser) {
		int status = profeser.create(newUser);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Profesor creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El usuario ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Endpoint de login para profesores.
	 *
	 * @param loginRequest objeto {@link ProfesorDTO} con usuario y contraseña.
	 * @return ResponseEntity con: - 200 (OK) si las credenciales son correctas. -
	 *         404 (NOT FOUND) si el usuario no existe. - 401 (UNAUTHORIZED) si la
	 *         contraseña es incorrecta. - 500 (INTERNAL SERVER ERROR) en caso de
	 *         error.
	 */
	@PostMapping(path = "/loginprofesor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginProfesor(@RequestBody ProfesorDTO loginRequest) {
		try {
			if (loginRequest.getUsuario() == null || loginRequest.getUsuario().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Usuario es requerido\"}", HttpStatus.BAD_REQUEST);
			}

			if (loginRequest.getContrasenia() == null || loginRequest.getContrasenia().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Contraseña es requerida\"}", HttpStatus.BAD_REQUEST);
			}

			int authResult = profeser.authenticateProfesor(loginRequest.getUsuario(), loginRequest.getContrasenia());

			switch (authResult) {
			case 0:
				String response = String.format(
						"{\"message\": \"Login exitoso\", \"usuario\": \"%s\", \"rol\": \"Profesor\"}",
						loginRequest.getUsuario());
				return new ResponseEntity<>(response, HttpStatus.OK);
			case 1:
				return new ResponseEntity<>("{\"error\": \"Usuario no encontrado\"}", HttpStatus.NOT_FOUND);
			case 2:
				return new ResponseEntity<>("{\"error\": \"Contraseña incorrecta\"}", HttpStatus.UNAUTHORIZED);
			case 3:
				return new ResponseEntity<>("{\"error\": \"Error del sistema\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			default:
				return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{\"error\": \"Error interno del servidor\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Crea un nuevo profesor a partir de parámetros en la URL.
	 *
	 * @param usuario           nombre de usuario.
	 * @param contrasenia       contraseña del profesor.
	 * @param nivelDePermiso    nivel de permisos asignados.
	 * @param fechaDeNacimiento fecha de nacimiento en formato String.
	 * @return ResponseEntity con resultado de la creación.
	 */
	@PostMapping(path = "/createprofesor")
	public ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia,
			@RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
		ProfesorDTO newUser = new ProfesorDTO(null, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
		int status = profeser.create(newUser);

		switch (status) {
		case 0:
			return new ResponseEntity<>("Profesor creado exitosamente", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("El usuario ya existe", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los profesores registrados en el sistema.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de profesores si existen. -
	 *         204 (NO CONTENT) si no hay profesores registrados.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<ProfesorDTO>> getAll() {
		List<ProfesorDTO> users = profeser.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}

	/**
	 * Endpoint de prueba para verificar la disponibilidad del controlador.
	 *
	 * @return ResponseEntity con un mensaje de confirmación.
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("{\"message\": \"Endpoint Profesor funcionando\"}", HttpStatus.OK);
	}

	/**
	 * Actualiza la imagen de perfil de un profesor en formato Base64.
	 *
	 * @param request mapa que contiene "usuario" y "imagenBase64".
	 * @return ResponseEntity con: - 200 (OK) si la imagen fue actualizada. - 404
	 *         (NOT FOUND) si no se encontró el usuario. - 500 (INTERNAL SERVER
	 *         ERROR) en caso de error.
	 */
	@PostMapping(path = "/actualizarImagen", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizarImagen(@RequestBody Map<String, Object> request) {
		try {
			String usuario = (String) request.get("usuario");
			String imagenBase64 = (String) request.get("imagenBase64");

			int resultado = profeser.actualizarImagen(usuario, imagenBase64);

			if (resultado == 0) {
				return new ResponseEntity<>("{\"message\": \"Imagen actualizada\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("{\"error\": \"Usuario no encontrado\"}", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{\"error\": \"Error interno\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene la imagen de perfil de un profesor en formato Base64.
	 *
	 * @param usuario nombre del usuario del profesor.
	 * @return ResponseEntity con: - 200 (OK) y la imagen en Base64 si existe. - 404
	 *         (NOT FOUND) si no se encontró el usuario o no tiene imagen. - 500
	 *         (INTERNAL SERVER ERROR) en caso de error.
	 */
	@GetMapping("/obtenerImagen")
	public ResponseEntity<Map<String, String>> obtenerImagen(@RequestParam String usuario) {
		try {
			String imagen = profeser.obtenerImagen(usuario);
			Map<String, String> response = new HashMap<>();

			if (imagen != null && !imagen.isEmpty()) {
				response.put("imagenBase64", imagen);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
