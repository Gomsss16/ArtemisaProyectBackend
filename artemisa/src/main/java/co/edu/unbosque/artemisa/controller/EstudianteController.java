package co.edu.unbosque.artemisa.controller;

import java.util.List;

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
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/createestudiantejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody EstudianteDTO newUser) {
		int status = estuserv.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("{User create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created user, maybe username already in use}",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(path = "/createestudiante")
	ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia, @RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
		EstudianteDTO newUser = new EstudianteDTO( null, usuario,contrasenia, nivelDePermiso, fechaDeNacimiento);
		int status = estuserv.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("User create successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error on created user, maybe username already in use",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@GetMapping("/getall")
	ResponseEntity<List<EstudianteDTO>> getAll() {
		List<EstudianteDTO> users = estuserv.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}

}
