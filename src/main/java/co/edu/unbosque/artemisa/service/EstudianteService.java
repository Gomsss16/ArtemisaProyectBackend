package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.EstudianteDTO;
import co.edu.unbosque.artemisa.entity.Estudiante;
import co.edu.unbosque.artemisa.repository.EstudiateRepository;

@Service
public class EstudianteService implements CRUDOperation<EstudianteDTO> {

	@Autowired
	private EstudiateRepository estudianterepo;
	@Autowired
	private ModelMapper modelMapper;

	public EstudianteService() {
	}

	@Override
	public int create(EstudianteDTO data) {
		System.out.println("=== CREAR ESTUDIANTE ===");
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

			Estudiante entity = modelMapper.map(data, Estudiante.class);

			if (findUsernameAlreadyTaken(entity)) {
				System.out.println("ERROR: Usuario ya existe - " + data.getUsuario());
				return 1;
			} else {
				estudianterepo.save(entity);
				System.out.println("SUCCESS: Estudiante creado - " + data.getUsuario());
				return 0;
			}
		} catch (Exception e) {
			System.err.println("ERROR en creación: " + e.getMessage());
			e.printStackTrace();
			return 2;
		}
	}

	public int authenticateEstudiante(String username, String password) {
		System.out.println("=== LOGIN ESTUDIANTE ===");
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

			Optional<Estudiante> estudianteFound = estudianterepo.findByUsuario(username);
			System.out.println("Usuario encontrado: " + estudianteFound.isPresent());

			if (estudianteFound.isEmpty()) {
				System.out.println("ERROR: Usuario no encontrado - " + username);
				return 1;
			}

			Estudiante estudiante = estudianteFound.get();
			String storedPassword = estudiante.getContrasenia();
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
	public List<EstudianteDTO> getAll() {
		System.out.println("=== OBTENER ESTUDIANTES ===");

		try {
			List<Estudiante> entityList = estudianterepo.findAll();
			System.out.println("Encontrados en BD: " + entityList.size());

			List<EstudianteDTO> dtoList = new ArrayList<>();
			entityList.forEach((entity) -> {
				EstudianteDTO dto = modelMapper.map(entity, EstudianteDTO.class);
				dtoList.add(dto);
			});

			System.out.println("DTOs creados: " + dtoList.size());
			return dtoList;

		} catch (Exception e) {
			System.err.println("Error obteniendo estudiantes: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public int deleteById(Long id) {
		try {
			if (estudianterepo.existsById(id)) {
				estudianterepo.deleteById(id);
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 2;
		}
	}

	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		try {
			Optional<Estudiante> found = estudianterepo.findById(id);

			if (!found.isPresent()) {
				return 2;
			}

			Optional<Estudiante> existingUser = estudianterepo.findByUsuario(newData.getUsuario());

			if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
				return 1;
			}

			Estudiante estudiante = found.get();
			estudiante.setUsuario(newData.getUsuario());
			estudiante.setContrasenia(newData.getContrasenia());
			estudiante.setNivelDePermiso(newData.getNivelDePermiso());

			if (newData.getFechaDeNacimiento() != null) {
				estudiante.setFechaDeNacimiento(newData.getFechaDeNacimiento());
			}

			estudianterepo.save(estudiante);
			return 0;

		} catch (Exception e) {
			return 3;
		}
	}

	@Override
	public long count() {
		return estudianterepo.count();
	}

	@Override
	public boolean exist(Long id) {
		return estudianterepo.existsById(id);
	}

	public boolean findUsernameAlreadyTaken(Estudiante newUser) {
		try {
			Optional<Estudiante> found = estudianterepo.findByUsuario(newUser.getUsuario());
			boolean exists = found.isPresent();
			System.out.println("Usuario duplicado: " + exists);
			return exists;
		} catch (Exception e) {
			System.err.println("Error verificando duplicado: " + e.getMessage());
			return false;
		}
	}
}
