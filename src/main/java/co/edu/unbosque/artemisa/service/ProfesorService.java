package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.ProfesorDTO;
import co.edu.unbosque.artemisa.entity.Profesor;
import co.edu.unbosque.artemisa.repository.ProfesorRepository;

@Service
public class ProfesorService implements CRUDOperation<ProfesorDTO> {

    @Autowired
    private ProfesorRepository proferepo;
    @Autowired
    private ModelMapper modelMapper;

    public ProfesorService() {
    }

    @Override
    public int create(ProfesorDTO data) {
        System.out.println("=== CREAR PROFESOR ===");
        System.out.println("Usuario: " + data.getUsuario());
        
        try {
            if (data.getUsuario() == null || data.getUsuario().trim().isEmpty()) {
                System.out.println("ERROR: Usuario vacío");
                return 2;
            }
            
            if (data.getContrasenia() == null || data.getContrasenia().trim().isEmpty()) {
                System.out.println("ERROR: Contraseña vacía");
                return 2;
            }

            Profesor entity = modelMapper.map(data, Profesor.class);
            
            if (findUsernameAlreadyTaken(entity)) {
                System.out.println("ERROR: Usuario ya existe - " + data.getUsuario());
                return 1;
            } else {
                proferepo.save(entity);
                System.out.println("SUCCESS: Profesor creado - " + data.getUsuario());
                return 0;
            }
        } catch (Exception e) {
            System.err.println("ERROR en creación: " + e.getMessage());
            e.printStackTrace();
            return 2;
        }
    }

    public int authenticateProfesor(String username, String password) {
        System.out.println("=== LOGIN PROFESOR ===");
        System.out.println("Usuario: " + username);
        System.out.println("Password length: " + (password != null ? password.length() : 0));
        
        try {
            if (username == null || username.trim().isEmpty()) {
                System.out.println("ERROR: Username vacío");
                return 1;
            }
            
            if (password == null || password.trim().isEmpty()) {
                System.out.println("ERROR: Password vacío");
                return 2;
            }
            
            Optional<Profesor> profesorFound = proferepo.findByUsuario(username);
            System.out.println("Usuario encontrado: " + profesorFound.isPresent());
            
            if (profesorFound.isEmpty()) {
                System.out.println("ERROR: Usuario no encontrado - " + username);
                return 1;
            }
            
            Profesor profesor = profesorFound.get();
            String storedPassword = profesor.getContrasenia();
            System.out.println("Password almacenado obtenido");
            System.out.println("Passwords coinciden: " + password.equals(storedPassword));
            
            if (password.equals(storedPassword)) {
                System.out.println("SUCCESS: Login exitoso - " + username);
                return 0;
            } else {
                System.out.println("ERROR: Contraseña incorrecta - " + username);
                return 2;
            }
            
        } catch (Exception e) {
            System.err.println("ERROR en login: " + e.getMessage());
            e.printStackTrace();
            return 3;
        }
    }

    @Override
    public List<ProfesorDTO> getAll() {
        System.out.println("=== OBTENER PROFESORES ===");
        
        try {
            List<Profesor> entityList = proferepo.findAll();
            System.out.println("Encontrados en BD: " + entityList.size());
            
            List<ProfesorDTO> dtoList = new ArrayList<>();
            entityList.forEach((entity) -> {
                ProfesorDTO dto = modelMapper.map(entity, ProfesorDTO.class);
                dtoList.add(dto);
            });
            
            System.out.println("DTOs creados: " + dtoList.size());
            return dtoList;
            
        } catch (Exception e) {
            System.err.println("Error obteniendo profesores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            if (proferepo.existsById(id)) {
                proferepo.deleteById(id);
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 2;
        }
    }

    @Override
    public int updateById(Long id, ProfesorDTO newData) {
        try {
            Optional<Profesor> found = proferepo.findById(id);
            
            if (!found.isPresent()) {
                return 2;
            }
            
            Optional<Profesor> existingUser = proferepo.findByUsuario(newData.getUsuario());
            
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                return 1;
            }
            
            Profesor profesor = found.get();
            profesor.setUsuario(newData.getUsuario());
            profesor.setContrasenia(newData.getContrasenia());
            profesor.setNivelDePermiso(newData.getNivelDePermiso());
            
            if (newData.getFechaDeNacimiento() != null) {
                profesor.setFechaDeNacimiento(newData.getFechaDeNacimiento());
            }
            
            proferepo.save(profesor);
            return 0;
            
        } catch (Exception e) {
            return 3;
        }
    }

    @Override
    public long count() {
        return proferepo.count();
    }

    @Override
    public boolean exist(Long id) {
        return proferepo.existsById(id);
    }

    public boolean findUsernameAlreadyTaken(Profesor newUser) {
        try {
            Optional<Profesor> found = proferepo.findByUsuario(newUser.getUsuario());
            boolean exists = found.isPresent();
            System.out.println("Usuario duplicado: " + exists);
            return exists;
        } catch (Exception e) {
            System.err.println("Error verificando duplicado: " + e.getMessage());
            return false;
        }
    }
}
