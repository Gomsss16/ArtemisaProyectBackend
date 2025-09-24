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

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/temario" })
public class TemarioController {

	@Autowired
	private TemarioService temaserv;

	// Crear un temario con JSON
	@PostMapping(path = "/createtemariojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody TemarioDTO nuevoTemario) {
		System.out.println("=== ENDPOINT CREAR TEMARIO ===");
		System.out.println("Datos recibidos: " + nuevoTemario.toString());

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


	@PostMapping(path = "/createtemario")
	public ResponseEntity<String> createNew(@RequestParam String temaAlgoritmo, @RequestParam String tipo,
			@RequestParam String contenido, @RequestParam String codigo) {
		TemarioDTO nuevoTemario = new TemarioDTO(null, temaAlgoritmo,tipo,contenido,codigo );
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
	
    @GetMapping("/getall")
    public ResponseEntity<List<TemarioDTO>> getAll() {
        List<TemarioDTO> temario = temaserv.getAll();
        System.out.println("Temarios: " + temario.size());
        
        if (temario.isEmpty()) {
            return new ResponseEntity<>(temario, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(temario, HttpStatus.OK);
        }
    }
    
    
    @GetMapping("/getbytipo")
    public ResponseEntity<List<TemarioDTO>> getByTipo(@RequestParam String tipo) {
        List<TemarioDTO> temarios = temaserv.getByTipo(tipo);

        if (temarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(temarios, HttpStatus.OK);
        }
    }
    
	@DeleteMapping("/deletebyTema")
	ResponseEntity<String> deleteByTema(@RequestParam  String temaAlgoritmo) {
		int status = temaserv.deleteByTema(temaAlgoritmo);
		if (status == 0) {
			return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
		} else{
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

}
