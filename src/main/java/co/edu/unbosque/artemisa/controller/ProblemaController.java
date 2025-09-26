package co.edu.unbosque.artemisa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.artemisa.dto.ProblemaDTO;
import co.edu.unbosque.artemisa.service.ProblemaService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST para la gestión de problemas de programación.
 * 
 * Ofrece endpoints para:
 * <ul>
 * <li>Crear problemas (por JSON o por parámetros).</li>
 * <li>Obtener todos los problemas registrados.</li>
 * <li>Eliminar problemas por título.</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/problema" })
public class ProblemaController {

	@Autowired
	private ProblemaService probleServ;

	/**
	 * Crea un nuevo problema a partir de un objeto JSON.
	 *
	 * @param nuevoProblema objeto {@link ProblemaDTO} con los datos del problema.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el problema ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createproblemajson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody ProblemaDTO nuevoProblema) {
		int status = probleServ.create(nuevoProblema);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Problema creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El Problema ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Crea un nuevo problema utilizando parámetros en la URL.
	 *
	 * @param titulo     título del problema.
	 * @param dificultad nivel de dificultad del problema.
	 * @param tema       tema o categoría del problema.
	 * @param juez       juez online asociado al problema.
	 * @param link       enlace del problema en el juez online.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el problema ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createproblema")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam int dificultad,
			@RequestParam String tema, @RequestParam String juez, @RequestParam String link) {
		ProblemaDTO nuevoProblema = new ProblemaDTO(null, titulo, dificultad, tema, juez, link);
		int status = probleServ.create(nuevoProblema);

		switch (status) {
		case 0:
			return new ResponseEntity<>("Problema creado exitosamente", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("El Problema ya existe", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los problemas registrados en el sistema.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de problemas si existen. - 204
	 *         (NO CONTENT) si no hay problemas.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<ProblemaDTO>> getAll() {
		List<ProblemaDTO> problemas = probleServ.getAll();

		if (problemas.isEmpty()) {
			return new ResponseEntity<>(problemas, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(problemas, HttpStatus.OK);
		}
	}

	/**
	 * Elimina un problema a partir de su título.
	 *
	 * @param titulo título del problema a eliminar.
	 * @return ResponseEntity con: - 202 (ACCEPTED) si fue eliminado correctamente.
	 *         - 404 (NOT FOUND) si no se encontró el problema.
	 */
	@DeleteMapping("/deletebyTitulo")
	ResponseEntity<String> deleteByTitulo(@RequestParam String titulo) {
		int status = probleServ.deleteByTema(titulo); // ⚠️ revisar si debería ser deleteByTitulo
		if (status == 0) {
			return new ResponseEntity<>("Problema deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}
}
