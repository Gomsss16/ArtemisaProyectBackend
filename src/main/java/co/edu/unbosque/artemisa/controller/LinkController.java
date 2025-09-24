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

import co.edu.unbosque.artemisa.dto.LinkDTO;
import co.edu.unbosque.artemisa.service.LinkService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/link" })
public class LinkController {

	@Autowired
	private LinkService linkserv;

	@PostMapping(path = "/createlinkjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody LinkDTO nuevoLink) {
		System.out.println("Datos recibidos: " + nuevoLink.toString());

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

	// ← ENDPOINT FALTANTE 1
	@GetMapping("/getall")
	public ResponseEntity<List<LinkDTO>> getAll() {
		List<LinkDTO> links = linkserv.getAll();
		System.out.println("Links: " + links.size());

		if (links.isEmpty()) {
			return new ResponseEntity<>(links, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(links, HttpStatus.OK);
		}
	}

	// ← ENDPOINT FALTANTE 2
	@DeleteMapping("/deletebyTitle")
	ResponseEntity<String> deleteByTitle(@RequestParam String title) {
		int status = linkserv.deleteByTitle(title);
		if (status == 0) {
			return new ResponseEntity<>("Link deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/createlink")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String descripcion,
			@RequestParam String enlace, @RequestParam("imagen") MultipartFile imagen) {

		try {
			LinkDTO nuevoLink = new LinkDTO(titulo, descripcion, enlace, null);
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
