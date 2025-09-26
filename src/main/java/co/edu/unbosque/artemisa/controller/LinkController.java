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
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.unbosque.artemisa.dto.LinkDTO;
import co.edu.unbosque.artemisa.service.LinkService;

/**
 * Controlador REST para la gestión de enlaces.
 * 
 * Ofrece endpoints para:
 * <ul>
 * <li>Crear enlaces (mediante JSON o parámetros en la URL).</li>
 * <li>Obtener todos los enlaces registrados.</li>
 * <li>Eliminar enlaces por título.</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/link" })
public class LinkController {

	@Autowired
	private LinkService linkserv;

	/**
	 * Crea un nuevo enlace a partir de un objeto JSON.
	 *
	 * @param nuevoLink objeto {@link LinkDTO} con la información del enlace.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el enlace ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createlinkjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody LinkDTO nuevoLink) {
		int status = linkserv.create(nuevoLink);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Link creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El link ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los enlaces registrados.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de enlaces si existen. - 204
	 *         (NO CONTENT) si no hay enlaces.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<LinkDTO>> getAll() {
		List<LinkDTO> links = linkserv.getAll();

		if (links.isEmpty()) {
			return new ResponseEntity<>(links, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(links, HttpStatus.OK);
		}
	}

	/**
	 * Elimina un enlace según su título.
	 *
	 * @param title título del enlace a eliminar.
	 * @return ResponseEntity con: - 202 (ACCEPTED) si fue eliminado correctamente.
	 *         - 404 (NOT FOUND) si no se encontró el enlace.
	 */
	@DeleteMapping("/deletebyTitle")
	ResponseEntity<String> deleteByTitle(@RequestParam String title) {
		int status = linkserv.deleteByTitle(title);
		if (status == 0) {
			return new ResponseEntity<>("Link deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crea un nuevo enlace utilizando parámetros enviados en la URL.
	 *
	 * @param titulo       título del enlace.
	 * @param descripcion  descripción del enlace.
	 * @param enlace       URL asociada al enlace.
	 * @param imagenBase64 imagen asociada al enlace en formato Base64.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el enlace ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createlink")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String descripcion,
			@RequestParam String enlace, @RequestParam String imagenBase64) {
		try {
			LinkDTO nuevoLink = new LinkDTO(null, titulo, descripcion, enlace, imagenBase64);
			int status = linkserv.create(nuevoLink);

			switch (status) {
			case 0:
				return new ResponseEntity<>("Link creado exitosamente", HttpStatus.CREATED);
			case 1:
				return new ResponseEntity<>("El link ya existe", HttpStatus.CONFLICT);
			case 2:
				return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
			default:
				return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al procesar archivos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
