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

    @PostMapping(path = "/createadminjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewWithJSON(@RequestBody AdministradorDTO newUser) {
        System.out.println("=== ENDPOINT CREAR ADMIN ===");
        System.out.println("Datos recibidos: " + newUser.toString());
        
        int status = adminserv.create(newUser);

        switch (status) {
            case 0:
                System.out.println("Admin creado exitosamente");
                return new ResponseEntity<>("{\"message\": \"Administrador creado exitosamente\"}", HttpStatus.CREATED);
            case 1:
                System.out.println("Admin ya existe");
                return new ResponseEntity<>("{\"error\": \"El usuario ya existe\"}", HttpStatus.CONFLICT);
            case 2:
                System.out.println("Error de datos");
                return new ResponseEntity<>("{\"error\": \"Datos inválidos\"}", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/loginadmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginAdmin(@RequestBody AdministradorDTO loginRequest) {
        System.out.println("=== ENDPOINT LOGIN ADMIN ===");
        System.out.println("Usuario: " + loginRequest.getUsuario());
        
        try {
            if (loginRequest.getUsuario() == null || loginRequest.getUsuario().trim().isEmpty()) {
                return new ResponseEntity<>("{\"error\": \"Usuario es requerido\"}", HttpStatus.BAD_REQUEST);
            }
            
            if (loginRequest.getContrasenia() == null || loginRequest.getContrasenia().trim().isEmpty()) {
                return new ResponseEntity<>("{\"error\": \"Contraseña es requerida\"}", HttpStatus.BAD_REQUEST);
            }
            
            int authResult = adminserv.authenticateAdmin(loginRequest.getUsuario(), loginRequest.getContrasenia());
            System.out.println("Resultado autenticación: " + authResult);
            
            switch (authResult) {
                case 0:
                    System.out.println("Login exitoso");
                    String response = String.format(
                        "{\"message\": \"Login exitoso\", \"usuario\": \"%s\", \"rol\": \"Administrador\"}", 
                        loginRequest.getUsuario()
                    );
                    return new ResponseEntity<>(response, HttpStatus.OK);
                    
                case 1:
                    System.out.println("Usuario no encontrado");
                    return new ResponseEntity<>("{\"error\": \"Usuario no encontrado\"}", HttpStatus.NOT_FOUND);
                    
                case 2:
                    System.out.println("Contraseña incorrecta");
                    return new ResponseEntity<>("{\"error\": \"Contraseña incorrecta\"}", HttpStatus.UNAUTHORIZED);
                    
                case 3:
                    System.out.println("Error del sistema");
                    return new ResponseEntity<>("{\"error\": \"Error del sistema\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                    
                default:
                    return new ResponseEntity<>("{\"error\": \"Error desconocido\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
        } catch (Exception e) {
            System.err.println("Excepción en login: " + e.getMessage());
            return new ResponseEntity<>("{\"error\": \"Error interno del servidor\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/createadmin")
    public ResponseEntity<String> createNew(@RequestParam String usuario, @RequestParam String contrasenia, 
            @RequestParam String nivelDePermiso, @RequestParam String fechaDeNacimiento) {
        AdministradorDTO newUser = new AdministradorDTO(null, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
        int status = adminserv.create(newUser);

        switch (status) {
            case 0:
                return new ResponseEntity<>("Administrador creado exitosamente", HttpStatus.CREATED);
            case 1:
                return new ResponseEntity<>("El usuario ya existe", HttpStatus.CONFLICT);
            case 2:
                return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AdministradorDTO>> getAll() {
        List<AdministradorDTO> users = adminserv.getAll();
        System.out.println("Administradores obtenidos: " + users.size());
        
        if (users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("{\"message\": \"Endpoint Administrador funcionando\"}", HttpStatus.OK);
    }
}
