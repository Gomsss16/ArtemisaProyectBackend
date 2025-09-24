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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.service.LibroService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/libro" })
public class LibroController {

	@Autowired
	private LibroService libroserv;

	@PostMapping(path = "/createlibrojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody LibroDTO nuevoLibro) {
		System.out.println("Datos recibidos: " + nuevoLibro.toString());

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

	@GetMapping("/getall")
	public ResponseEntity<List<LibroDTO>> getAll() {
		List<LibroDTO> libros = libroserv.getAll();
		System.out.println("Libros: " + libros.size());

		if (libros.isEmpty()) {
			return new ResponseEntity<>(libros, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(libros, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deletebyTitle")
	ResponseEntity<String> deleteByTitle(@RequestParam String title) {
		int status = libroserv.deleteByTitle(title);
		if (status == 0) {
			return new ResponseEntity<>("Libro deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/createlibro")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String autor,
			@RequestParam String descripcion, @RequestParam("pdf") MultipartFile pdf,
			@RequestParam("imagen") MultipartFile imagen) {

		try {
			LibroDTO nuevoLibro = new LibroDTO(titulo, autor, descripcion, null, null);
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
