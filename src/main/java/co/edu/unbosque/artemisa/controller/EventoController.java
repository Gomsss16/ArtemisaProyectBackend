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

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/evento" })
public class EventoController {

    @Autowired
    private EventoService eventoserv;

    @PostMapping(path = "/createeventojson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewWithJSON(@RequestBody EventoDTO nuevoEvento) {
        System.out.println("Datos recibidos: " + nuevoEvento.toString());

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

    @GetMapping("/getall")
    public ResponseEntity<List<EventoDTO>> getAll() {
        List<EventoDTO> eventos = eventoserv.getAll();
        System.out.println("Eventos: " + eventos.size());

        if (eventos.isEmpty()) {
            return new ResponseEntity<>(eventos, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletebyTitle")
    ResponseEntity<String> deleteByTitle(@RequestParam String title) {
        int status = eventoserv.deleteByTitle(title);
        if (status == 0) {
            return new ResponseEntity<>("Evento deleted successfully", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/createevento")
    public ResponseEntity<String> createNew(@RequestParam String titulo, @RequestParam String descripcion,
            @RequestParam String tipo, @RequestParam String fecha, @RequestParam String enlace, @RequestParam String ubicacion) {

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
