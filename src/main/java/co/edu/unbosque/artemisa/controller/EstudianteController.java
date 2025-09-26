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

import co.edu.unbosque.artemisa.dto.EstudianteDTO;
import co.edu.unbosque.artemisa.service.EstudianteService;

/**
 * Controlador REST para gestionar las operaciones relacionadas con estudiantes.
 * 
 * Proporciona endpoints para:
 * <ul>
 * <li>Crear estudiantes</li>
 * <li>Autenticar estudiantes (login)</li>
 * <li>Listar estudiantes</li>
 * <li>Actualizar y obtener imágenes de perfil</li>
 * <li>Probar la conectividad del servicio</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/estudiante" })
public class EstudianteController {

	@Autowired
	private EstudianteService estuserv;

	public EstudianteController() {
	}

	/**
	 * Crea un nuevo estudiante a partir de un objeto JSON.
	 *
	 * @param newUser objeto {@link EstudianteDTO} con la información del nuevo
	 *                estudiante.
	 * @return ResponseEntity con mensaje en JSON: - 201 (CREATED) si se creó
	 *         correctamente. - 409 (CONFLICT) si el usuario ya existe. - 400 (BAD
	 *         REQUEST) si los datos son inválidos. - 500 (INTERNAL SERVER ERROR) en
	 *         caso de error desconocido.
	 */
	@PostMapping(path = "/createestudiantejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody EstudianteDTO newUser) {
		int status = estuserv.create(newUser);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Estudiante creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El usuario ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Autentica a un estudiante con usuario y contraseña.
	 *
	 * @param loginRequest objeto {@link EstudianteDTO} con usuario y contraseña.
	 * @return ResponseEntity con mensaje en JSON: - 200 (OK) si el login es
	 *         exitoso. - 404 (NOT FOUND) si el usuario no existe. - 401
	 *         (UNAUTHORIZED) si la contraseña es incorrecta. - 400 (BAD REQUEST) si
	 *         faltan datos requeridos. - 500 (INTERNAL SERVER ERROR) en caso de
	 *         error.
	 */
	@PostMapping(path = "/loginestudiantejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginEstudiante(@RequestBody EstudianteDTO loginRequest) {
		try {
			if (loginRequest.getUsuario() == null || loginRequest.getUsuario().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Usuario es requerido\"}", HttpStatus.BAD_REQUEST);
			}

			if (loginRequest.getContrasenia() == null || loginRequest.getContrasenia().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Contraseña es requerida\"}", HttpStatus.BAD_REQUEST);
			}

			int authResult = estuserv.authenticateEstudiante(loginRequest.getUsuario(), loginRequest.getContrasenia());

			switch (authResult) {
			case 0:
				String response = String.format(
						"{\"message\": \"Login exitoso\", \"usuario\": \"%s\", \"rol\": \"Estudiante\"}",
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
	 * Crea un nuevo estudiante a partir de parámetros enviados en la URL.
	 *
	 * @param usuario           nombre de usuario.
	 * @param contrasenia       contraseña del estudiante.
	 * @param nivelDePermiso    nivel de permisos asignado.
	 * @param fechaDeNacimiento fecha de nacimiento del estudiante.
	 * @return ResponseEntity con el resultado de la operación: - 201 (CREATED) si
	 *         se creó correctamente. - 409 (CONFLICT) si el usuario ya existe. -
	 *         400 (BAD REQUEST) si los datos son inválidos. - 500 (INTERNAL SERVER
	 *         ERROR) en caso de error.
	 */
	@PostMapping(path = "/createestudiante")
	public ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia,
			@RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
		EstudianteDTO newUser = new EstudianteDTO(null, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
		int status = estuserv.create(newUser);

		switch (status) {
		case 0:
			return new ResponseEntity<>("Estudiante creado exitosamente", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("El usuario ya existe", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene la lista de todos los estudiantes registrados.
	 *
	 * @return ResponseEntity con: - 200 (OK) y la lista de estudiantes si existen.
	 *         - 204 (NO CONTENT) si no hay estudiantes registrados.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<EstudianteDTO>> getAll() {
		List<EstudianteDTO> users = estuserv.getAll();

		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}

	/**
	 * Endpoint de prueba para verificar el estado del controlador.
	 *
	 * @return ResponseEntity con un mensaje de confirmación (200 OK).
	 */
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("{\"message\": \"Endpoint Estudiante funcionando\"}", HttpStatus.OK);
	}

	/**
	 * Actualiza la imagen de perfil de un estudiante.
	 *
	 * @param request mapa con las claves: - "usuario": nombre del usuario. -
	 *                "imagenBase64": cadena de la imagen en Base64.
	 * @return ResponseEntity con: - 200 (OK) si la imagen fue actualizada. - 404
	 *         (NOT FOUND) si el usuario no existe. - 500 (INTERNAL SERVER ERROR) en
	 *         caso de error.
	 */
	@PostMapping(path = "/actualizarImagen", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizarImagen(@RequestBody Map<String, Object> request) {
		try {
			String usuario = (String) request.get("usuario");
			String imagenBase64 = (String) request.get("imagenBase64");

			int resultado = estuserv.actualizarImagen(usuario, imagenBase64);

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
	 * Obtiene la imagen de perfil de un estudiante.
	 *
	 * @param usuario nombre de usuario del estudiante.
	 * @return ResponseEntity con: - 200 (OK) y la imagen en Base64 si existe. - 404
	 *         (NOT FOUND) si no se encuentra la imagen o el usuario. - 500
	 *         (INTERNAL SERVER ERROR) en caso de error.
	 */
	@GetMapping("/obtenerImagen")
	public ResponseEntity<Map<String, String>> obtenerImagen(@RequestParam String usuario) {
		try {
			String imagen = estuserv.obtenerImagen(usuario);
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
