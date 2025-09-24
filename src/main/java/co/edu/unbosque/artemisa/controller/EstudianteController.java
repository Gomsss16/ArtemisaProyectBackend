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

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/estudiante" })
public class EstudianteController {

	@Autowired
	private EstudianteService estuserv;

	public EstudianteController() {
	}

	@PostMapping(path = "/createestudiantejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody EstudianteDTO newUser) {
		System.out.println("=== ENDPOINT CREAR ESTUDIANTE ===");
		System.out.println("Datos recibidos: " + newUser.toString());

		int status = estuserv.create(newUser);

		switch (status) {
		case 0:
			System.out.println("Estudiante creado exitosamente");
			return new ResponseEntity<>("{\"message\": \"Estudiante creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			System.out.println("Estudiante ya existe");
			return new ResponseEntity<>("{\"error\": \"El usuario ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			System.out.println("Error de datos");
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/loginestudiantejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginEstudiante(@RequestBody EstudianteDTO loginRequest) {
		System.out.println("=== ENDPOINT LOGIN ESTUDIANTE ===");
		System.out.println("Usuario: " + loginRequest.getUsuario());

		try {
			if (loginRequest.getUsuario() == null || loginRequest.getUsuario().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Usuario es requerido\"}", HttpStatus.BAD_REQUEST);
			}

			if (loginRequest.getContrasenia() == null || loginRequest.getContrasenia().trim().isEmpty()) {
				return new ResponseEntity<>("{\"error\": \"Contraseña es requerida\"}", HttpStatus.BAD_REQUEST);
			}

			int authResult = estuserv.authenticateEstudiante(loginRequest.getUsuario(), loginRequest.getContrasenia());
			System.out.println("Resultado autenticación: " + authResult);

			switch (authResult) {
			case 0:
				System.out.println("Login exitoso");
				String response = String.format(
						"{\"message\": \"Login exitoso\", \"usuario\": \"%s\", \"rol\": \"Estudiante\"}",
						loginRequest.getUsuario());
				return new ResponseEntity<>(response, HttpStatus.OK);

			case 1:
				System.out.println("Usuario no encontrado");
				return new ResponseEntity<>("{\"error\": \"Usuario no encontrado\"}", HttpStatus.NOT_FOUND);

			case 2:
				System.out.println("Contraseña incorrecta");
				return new ResponseEntity<>("{\"error\": \"Contraseña incorrecta\"}", HttpStatus.UNAUTHORIZED);

			case 3:
				System.out.println("Error del sistema");
				return new ResponseEntity<>("{\"error\": \"Error del sistema\"}", HttpStatus.INTERNAL_SERVER_ERROR);

			default:
				return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			System.err.println("Excepción en login: " + e.getMessage());
			return new ResponseEntity<>("{\"error\": \"Error interno del servidor\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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

	@GetMapping("/getall")
	public ResponseEntity<List<EstudianteDTO>> getAll() {
		List<EstudianteDTO> users = estuserv.getAll();
		System.out.println("Estudiantes obtenidos: " + users.size());

		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("{\"message\": \"Endpoint Estudiante funcionando\"}", HttpStatus.OK);
	}
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
