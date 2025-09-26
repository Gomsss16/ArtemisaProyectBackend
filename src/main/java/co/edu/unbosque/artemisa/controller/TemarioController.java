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

import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.service.TemarioService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST para la gestión de temarios.
 * 
 * Proporciona endpoints para:
 * <ul>
 * <li>Crear temarios (JSON o parámetros).</li>
 * <li>Obtener todos los temarios.</li>
 * <li>Filtrar temarios por tipo.</li>
 * <li>Eliminar temarios por tema.</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/temario" })
public class TemarioController {

	@Autowired
	private TemarioService temaserv;

	/**
	 * Crea un nuevo temario a partir de un objeto JSON.
	 *
	 * @param nuevoTemario objeto {@link TemarioDTO} con la información del temario.
	 * @return ResponseEntity con: - 201 (CREATED) si fue creado exitosamente. - 409
	 *         (CONFLICT) si ya existe. - 400 (BAD REQUEST) si los datos son
	 *         inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createtemariojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody TemarioDTO nuevoTemario) {
		int status = temaserv.create(nuevoTemario);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Temario creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El temario ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Crea un nuevo temario a partir de parámetros en la URL.
	 *
	 * @param temaAlgoritmo nombre del tema del algoritmo.
	 * @param tipo          tipo de temario (ejemplo: teoría, práctica, ejemplo).
	 * @param contenido     contenido textual del temario.
	 * @param codigo        fragmento de código asociado.
	 * @return ResponseEntity con resultado de la creación.
	 */
	@PostMapping(path = "/createtemario")
	public ResponseEntity<String> createNew(@RequestParam String temaAlgoritmo, @RequestParam String tipo,
			@RequestParam String contenido, @RequestParam String codigo) {
		TemarioDTO nuevoTemario = new TemarioDTO(null, temaAlgoritmo, tipo, contenido, codigo);
		int status = temaserv.create(nuevoTemario);

		switch (status) {
		case 0:
			return new ResponseEntity<>("Temario creado exitosamente", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("El temario ya existe", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los temarios registrados en el sistema.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de temarios si existen. - 204
	 *         (NO CONTENT) si no hay registros.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<TemarioDTO>> getAll() {
		List<TemarioDTO> temario = temaserv.getAll();
		if (temario.isEmpty()) {
			return new ResponseEntity<>(temario, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(temario, HttpStatus.OK);
		}
	}

	/**
	 * Obtiene los temarios filtrados por tipo.
	 *
	 * @param tipo tipo de temario a buscar (ejemplo: teoría, práctica).
	 * @return ResponseEntity con: - 200 (OK) y lista de temarios del tipo
	 *         especificado. - 204 (NO CONTENT) si no existen registros con ese
	 *         tipo.
	 */
	@GetMapping("/getbytipo")
	public ResponseEntity<List<TemarioDTO>> getByTipo(@RequestParam String tipo) {
		List<TemarioDTO> temarios = temaserv.getByTipo(tipo);
		if (temarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(temarios, HttpStatus.OK);
		}
	}

	/**
	 * Elimina un temario a partir de su tema.
	 *
	 * @param temaAlgoritmo nombre del tema a eliminar.
	 * @return ResponseEntity con: - 202 (ACCEPTED) si fue eliminado correctamente.
	 *         - 404 (NOT FOUND) si no existe el temario.
	 */
	@DeleteMapping("/deletebyTema")
	public ResponseEntity<String> deleteByTema(@RequestParam String temaAlgoritmo) {
		int status = temaserv.deleteByTema(temaAlgoritmo);
		if (status == 0) {
			return new ResponseEntity<>("Temario eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar", HttpStatus.NOT_FOUND);
		}
	}
}
