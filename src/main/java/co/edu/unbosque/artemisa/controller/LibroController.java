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

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.service.LibroService;

/**
 * Controlador REST para la gestión de libros.
 * 
 * Proporciona endpoints para:
 * <ul>
 * <li>Crear libros (mediante JSON o parámetros en URL).</li>
 * <li>Obtener todos los libros registrados.</li>
 * <li>Eliminar libros por título.</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/libro" })
public class LibroController {

	@Autowired
	private LibroService libroserv;

	/**
	 * Crea un nuevo libro a partir de un objeto JSON.
	 *
	 * @param nuevoLibro objeto {@link LibroDTO} con la información del libro.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el libro ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createlibrojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody LibroDTO nuevoLibro) {
		int status = libroserv.create(nuevoLibro);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Libro creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El libro ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los libros registrados.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de libros si existen. - 204
	 *         (NO CONTENT) si no hay libros.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<LibroDTO>> getAll() {
		List<LibroDTO> libros = libroserv.getAll();

		if (libros.isEmpty()) {
			return new ResponseEntity<>(libros, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(libros, HttpStatus.OK);
		}
	}

	/**
	 * Elimina un libro según su título.
	 *
	 * @param title título del libro a eliminar.
	 * @return ResponseEntity con: - 202 (ACCEPTED) si fue eliminado correctamente.
	 *         - 404 (NOT FOUND) si no se encontró el libro.
	 */
	@DeleteMapping("/deletebyTitle")
	ResponseEntity<String> deleteByTitle(@RequestParam String title) {
		int status = libroserv.deleteByTitle(title);
		if (status == 0) {
			return new ResponseEntity<>("Libro deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crea un nuevo libro utilizando parámetros enviados en la URL.
	 *
	 * @param titulo       título del libro.
	 * @param author       autor del libro.
	 * @param descripcion  descripción del libro.
	 * @param enlace       enlace relacionado con el libro.
	 * @param imagenBase64 imagen del libro en formato Base64.
	 * @param pdfBase64    archivo PDF del libro en formato Base64.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el libro ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createlibro")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String author,
			@RequestParam String descripcion, @RequestParam String enlace, @RequestParam String imagenBase64,
			@RequestParam String pdfBase64) {
		try {
			LibroDTO nuevoLibro = new LibroDTO(null, titulo, author, descripcion, enlace, imagenBase64, pdfBase64);
			int status = libroserv.create(nuevoLibro);

			switch (status) {
			case 0:
				return new ResponseEntity<>("libro creado exitosamente", HttpStatus.CREATED);
			case 1:
				return new ResponseEntity<>("El libro ya existe", HttpStatus.CONFLICT);
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
