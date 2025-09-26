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

import co.edu.unbosque.artemisa.dto.EventoDTO;
import co.edu.unbosque.artemisa.service.EventoService;

/**
 * Controlador REST para gestionar los eventos.
 * 
 * Proporciona endpoints para:
 * <ul>
 * <li>Crear eventos (con JSON o parámetros en URL)</li>
 * <li>Obtener todos los eventos</li>
 * <li>Eliminar eventos por título</li>
 * </ul>
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/evento" })
public class EventoController {

	@Autowired
	private EventoService eventoserv;

	/**
	 * Crea un nuevo evento a partir de un objeto JSON.
	 *
	 * @param nuevoEvento objeto {@link EventoDTO} con la información del evento.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el evento ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createeventojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJSON(@RequestBody EventoDTO nuevoEvento) {
		int status = eventoserv.create(nuevoEvento);

		switch (status) {
		case 0:
			return new ResponseEntity<>("{\"message\": \"Evento creado exitosamente\"}", HttpStatus.CREATED);
		case 1:
			return new ResponseEntity<>("{\"error\": \"El evento ya existe\"}", HttpStatus.CONFLICT);
		case 2:
			return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
		default:
			return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene todos los eventos registrados.
	 *
	 * @return ResponseEntity con: - 200 (OK) y lista de eventos si existen. - 204
	 *         (NO CONTENT) si no hay eventos.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<EventoDTO>> getAll() {
		List<EventoDTO> eventos = eventoserv.getAll();

		if (eventos.isEmpty()) {
			return new ResponseEntity<>(eventos, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(eventos, HttpStatus.OK);
		}
	}

	/**
	 * Elimina un evento según su título.
	 *
	 * @param title título del evento a eliminar.
	 * @return ResponseEntity con: - 202 (ACCEPTED) si fue eliminado correctamente.
	 *         - 404 (NOT FOUND) si no se encontró el evento.
	 */
	@DeleteMapping("/deletebyTitle")
	ResponseEntity<String> deleteByTitle(@RequestParam String title) {
		int status = eventoserv.deleteByTitle(title);
		if (status == 0) {
			return new ResponseEntity<>("Evento deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crea un nuevo evento utilizando parámetros enviados en la URL.
	 *
	 * @param titulo      título del evento.
	 * @param descripcion descripción del evento.
	 * @param tipo        tipo de evento (ej. académico, cultural, deportivo).
	 * @param fecha       fecha del evento.
	 * @param enlace      enlace relacionado con el evento.
	 * @param ubicacion   ubicación donde se realizará el evento.
	 * @return ResponseEntity con: - 201 (CREATED) si se creó exitosamente. - 409
	 *         (CONFLICT) si el evento ya existe. - 400 (BAD REQUEST) si los datos
	 *         son inválidos. - 500 (INTERNAL SERVER ERROR) en caso de error.
	 */
	@PostMapping(path = "/createevento")
	public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String descripcion,
			@RequestParam String tipo, @RequestParam String fecha, @RequestParam String enlace,
			@RequestParam String ubicacion) {
		try {
			EventoDTO nuevoEvento = new EventoDTO(titulo, descripcion, tipo, null, enlace, ubicacion);
			int status = eventoserv.create(nuevoEvento);

			switch (status) {
			case 0:
				return new ResponseEntity<>("Evento creado exitosamente", HttpStatus.CREATED);
			case 1:
				return new ResponseEntity<>("El evento ya existe", HttpStatus.CONFLICT);
			case 2:
				return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
			default:
				return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al procesar evento", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
