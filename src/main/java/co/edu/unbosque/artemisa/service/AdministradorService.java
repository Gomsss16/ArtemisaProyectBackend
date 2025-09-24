package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.AdministradorDTO;
import co.edu.unbosque.artemisa.entity.Administrador;
import co.edu.unbosque.artemisa.repository.AdministradorRepository;
import co.edu.unbosque.artemisa.util.AESUtil;

@Service
public class AdministradorService implements CRUDOperation<AdministradorDTO> {

    @Autowired
    private AdministradorRepository adminrepo;
    @Autowired
    private ModelMapper modelMapper;	

    public AdministradorService() {
    }

    @Override
    public int create(AdministradorDTO data) {
        System.out.println("=== CREAR ADMINISTRADOR ===");
        System.out.println("Usuario: " + data.getUsuario());
        System.out.println("Rol: " + data.getNivelDePermiso());
        
        try {
            if (data.getUsuario() == null || data.getUsuario().trim().isEmpty()) {
                System.out.println("ERROR: Usuario vacío");
                return 2;
            }
            
            if (data.getContrasenia() == null || data.getContrasenia().trim().isEmpty()) {
                System.out.println("ERROR: Contraseña vacía");
                return 2;
            }
            
            Administrador entity = new Administrador();
            entity.setUsuario(AESUtil.encrypt(data.getUsuario()));
            entity.setContrasenia(AESUtil.encrypt(data.getContrasenia()));
            entity.setNivelDePermiso(AESUtil.encrypt(data.getNivelDePermiso()));
            
            // Manejar fecha de nacimiento opcional
            if (data.getFechaDeNacimiento() != null && !data.getFechaDeNacimiento().trim().isEmpty()) {
                entity.setFechaDeNacimiento(AESUtil.encrypt(data.getFechaDeNacimiento()));
            } else {
                entity.setFechaDeNacimiento(AESUtil.encrypt(""));
            }
            
            System.out.println("Datos encriptados correctamente");
            
            if (findUsernameAlreadyTaken(entity)) {
                System.out.println("ERROR: Usuario ya existe - " + data.getUsuario());
                return 1; 
            } else {
                adminrepo.save(entity);
                System.out.println("SUCCESS: Administrador creado - " + data.getUsuario());
                return 0; 
            }
        } catch (Exception e) {
            System.err.println("ERROR en creación: " + e.getMessage());
            e.printStackTrace();
            return 2; 
        }
    }

    public int authenticateAdmin(String username, String password) {
        System.out.println("=== LOGIN ADMINISTRADOR ===");
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
            
            String encryptedUsername = AESUtil.encrypt(username);
            System.out.println("Username encriptado: " + encryptedUsername);
            
            Optional<Administrador> adminFound = adminrepo.findByUsuario(encryptedUsername);
            System.out.println("Usuario encontrado: " + adminFound.isPresent());
            
            if (adminFound.isEmpty()) {
                System.out.println("ERROR: Usuario no encontrado - " + username);
                return 1; 
            }
            
            Administrador admin = adminFound.get();
            String decryptedPassword = AESUtil.decrypt(admin.getContrasenia());
            System.out.println("Password almacenado desencriptado obtenido");
            System.out.println("Passwords coinciden: " + password.equals(decryptedPassword));
            
            if (password.equals(decryptedPassword)) {
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
    public List<AdministradorDTO> getAll() {
        System.out.println("=== OBTENER ADMINISTRADORES ===");
        
        try {
            List<Administrador> entityList = adminrepo.findAll();
            System.out.println("Encontrados en BD: " + entityList.size());
            
            List<AdministradorDTO> dtoList = new ArrayList<>();
            
            entityList.forEach((entity) -> {
                try {
                    AdministradorDTO dto = new AdministradorDTO();
                    dto.setId(entity.getId());
                    dto.setUsuario(AESUtil.decrypt(entity.getUsuario()));
                    dto.setContrasenia(AESUtil.decrypt(entity.getContrasenia()));
                    dto.setNivelDePermiso(AESUtil.decrypt(entity.getNivelDePermiso()));
                    
                    if (entity.getFechaDeNacimiento() != null && !entity.getFechaDeNacimiento().trim().isEmpty()) {
                        dto.setFechaDeNacimiento(AESUtil.decrypt(entity.getFechaDeNacimiento()));
                    } else {
                        dto.setFechaDeNacimiento("");
                    }
                    
                    dtoList.add(dto);
                } catch (Exception e) {
                    System.err.println("Error desencriptando entidad: " + e.getMessage());
                }
            });
            
            System.out.println("DTOs creados: " + dtoList.size());
            return dtoList;
            
        } catch (Exception e) {
            System.err.println("Error obteniendo administradores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            if (adminrepo.existsById(id)) {
                adminrepo.deleteById(id);
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 2;
        }
    }

    @Override
    public int updateById(Long id, AdministradorDTO newData) {
        try {
            Optional<Administrador> found = adminrepo.findById(id);
            
            if (!found.isPresent()) {
                return 2;
            }
            
            String encryptedNewUsername = AESUtil.encrypt(newData.getUsuario());
            Optional<Administrador> existingUser = adminrepo.findByUsuario(encryptedNewUsername);
            
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                return 1;
            }
            
            Administrador admin = found.get();
            admin.setUsuario(AESUtil.encrypt(newData.getUsuario()));
            admin.setContrasenia(AESUtil.encrypt(newData.getContrasenia()));
            admin.setNivelDePermiso(AESUtil.encrypt(newData.getNivelDePermiso()));
            
            if (newData.getFechaDeNacimiento() != null) {
                admin.setFechaDeNacimiento(AESUtil.encrypt(newData.getFechaDeNacimiento()));
            }
            
            adminrepo.save(admin);
            return 0;
            
        } catch (Exception e) {
            return 3;
        }
    }

    @Override
    public long count() {
        return adminrepo.count();
    }

    @Override
    public boolean exist(Long id) {
        return adminrepo.existsById(id);
    }

    public int actualizarImagen(String usuario, String imagenBase64) {
        try {
            String usuarioEncriptado = AESUtil.encrypt(usuario); // ← ENCRIPTAR PRIMERO
            Optional<Administrador> found = adminrepo.findByUsuario(usuarioEncriptado);
            
            if (found.isPresent()) {
                Administrador admin = found.get();
                admin.setImagenPerfil(imagenBase64);
                adminrepo.save(admin);
                System.out.println("Imagen guardada para: " + usuario);
                return 0;
            }
            System.out.println("Usuario no encontrado: " + usuario);
            return 1;
            
        } catch (Exception e) {
            System.err.println("Error actualizando imagen: " + e.getMessage());
            return 2;
        }
    }

    public String obtenerImagen(String usuario) {
        try {
            String usuarioEncriptado = AESUtil.encrypt(usuario); // ← ENCRIPTAR PRIMERO
            Optional<Administrador> found = adminrepo.findByUsuario(usuarioEncriptado);
            
            if (found.isPresent()) {
                return found.get().getImagenPerfil();
            }
            return null;
            
        } catch (Exception e) {
            System.err.println("Error obteniendo imagen: " + e.getMessage());
            return null;
        }
    }


    public boolean findUsernameAlreadyTaken(Administrador newUser) {
        try {
            Optional<Administrador> found = adminrepo.findByUsuario(newUser.getUsuario());
            boolean exists = found.isPresent();
            System.out.println("Usuario duplicado: " + exists);
            return exists;
        } catch (Exception e) {
            System.err.println("Error verificando duplicado: " + e.getMessage());
            return false;
        }
    }
    
}
