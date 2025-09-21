package co.edu.unbosque.artemisa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.service.LibroService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	@PostMapping(path = "/createlibro")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String autor,
			@RequestParam String descripcion, @RequestParam("pdf") MultipartFile  pdf,@RequestParam("imagen") MultipartFile imagen) {
		
		try {
		LibroDTO nuevoLibro = new LibroDTO(null, titulo,autor,descripcion,pdf.getBytes() ,imagen.getBytes());
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
		}catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Error al procesar archivos", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
