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

import co.edu.unbosque.artemisa.dto.AdministradorDTO;
import co.edu.unbosque.artemisa.service.AdministradorService;



@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/admin" })

public class AdministradorController {

	@Autowired
	private AdministradorService adminserv;
	
	public AdministradorController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/createadminjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody AdministradorDTO newUser) {
		int status = adminserv.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("{User create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created user, maybe username already in use}",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(path = "/createadmin")
	ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia, @RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
		AdministradorDTO newUser = new AdministradorDTO( null, usuario,contrasenia, nivelDePermiso, fechaDeNacimiento);
		int status = adminserv.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("User create successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error on created user, maybe username already in use",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/getall")
	ResponseEntity<List<AdministradorDTO>> getAll() {
		List<AdministradorDTO> users = adminserv.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}
	
	
//	@PostMapping(path = { "crear" })
//	public ResponseEntity<String> crear(@RequestParam String nombre, int edad) {
//		AdministradorDTO newUser = new AdministradorDTO();
//		int status = adminserv.create(newUser);
//
//		if (status == 0) {
//			return new ResponseEntity<>("Usuario creado con exito", HttpStatus.CREATED);
//		} else if (status == 1) {
//			return new ResponseEntity<>("Error: El nombre de usuario ya existe", HttpStatus.CONFLICT);
//		} else if (status == 2) {
//			return new ResponseEntity<>("Error: Nombre invalido", HttpStatus.BAD_REQUEST);
//		} else if (status == 3) {
//			return new ResponseEntity<>("Error: Edad debe estar entre 1 y 120", HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>("Error al crear Usuario", HttpStatus.NOT_ACCEPTABLE);
//		}
//	}

	}
