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
public class EstudianteService implements CRUDOperation<EstudianteDTO>{
	
	@Autowired
	private EstudiateRepository estudianterepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public EstudianteService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(EstudianteDTO data) {
		Estudiante entity = modelMapper.map(data, Estudiante.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		} else {
			estudianterepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<EstudianteDTO> getAll() {
		List<Estudiante> entityList = estudianterepo.findAll();
		List<EstudianteDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EstudianteDTO dto = modelMapper.map(entity, EstudianteDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
	return 0;
	}

	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		Optional<Estudiante> found = estudianterepo.findById(id);
		Optional<Estudiante> newFound = estudianterepo.findByUsuario(newData.getUsuario());

		if (found.isPresent() && !newFound.isPresent()) {
			Estudiante temp = found.get();
			temp.setUsuario(newData.getUsuario());
			temp.setContrasenia(newData.getContrasenia());
			estudianterepo.save(temp);
			return 0;
		}
		if (found.isPresent() && newFound.isPresent()) {
			return 1;
		}
		if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return estudianterepo.existsById(id) ? true : false;
	}
	
	public boolean findUsernameAlreadyTaken(Estudiante newUser) {
		Optional<Estudiante> found = estudianterepo.findByUsuario(newUser.getUsuario());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
