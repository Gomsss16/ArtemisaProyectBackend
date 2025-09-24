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
import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.service.ProblemaService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/problema" })
public class ProblemaController {

	@Autowired
	private ProblemaService probleServ;

	@PostMapping(path = "/createproblemajson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody ProblemaDTO nuevoProblema) {

		System.out.println("Datos recibidos: " + nuevoProblema.toString());

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

	@GetMapping("/getall")
	public ResponseEntity<List<ProblemaDTO>> getAll() {
		List<ProblemaDTO> Problemas = probleServ.getAll();
		System.out.println("Problemas: " + Problemas.size());

		if (Problemas.isEmpty()) {
			return new ResponseEntity<>(Problemas, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(Problemas, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deletebyTitulo")
	ResponseEntity<String> deleteByTitulo(@RequestParam String titulo) {
		int status = probleServ.deleteByTema(titulo);
		if (status == 0) {
			return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

}
