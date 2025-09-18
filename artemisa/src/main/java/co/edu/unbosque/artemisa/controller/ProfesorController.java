package co.edu.unbosque.artemisa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import co.edu.unbosque.artemisa.dto.ProfesorDTO;
import co.edu.unbosque.artemisa.service.ProfesorService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/profesor" })
public class ProfesorController {
	
	@Autowired
	private ProfesorService profeser;
	
	public ProfesorController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/createprofesorjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody ProfesorDTO newUser) {
		int status = profeser.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("{User create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created user, maybe username already in use}",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(path = "/createprofesor")
	ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia, @RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
		ProfesorDTO newUser = new ProfesorDTO( null, usuario,contrasenia, nivelDePermiso, fechaDeNacimiento);
		int status = profeser.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("User create successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error on created user, maybe username already in use",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
