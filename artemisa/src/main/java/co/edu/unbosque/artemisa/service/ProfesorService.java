package co.edu.unbosque.artemisa.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.ProfesorDTO;
import co.edu.unbosque.artemisa.entity.Profesor;
import co.edu.unbosque.artemisa.repository.ProfesorRepository;

@Service
public class ProfesorService implements CRUDOperation<ProfesorDTO>{
	
	@Autowired
	private ProfesorRepository proferepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public ProfesorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(ProfesorDTO data) {
		Profesor entity = modelMapper.map(data, Profesor.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		} else {
			proferepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<ProfesorDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, ProfesorDTO newData) {
		Optional<Profesor> found = proferepo.findById(id);
		Optional<Profesor> newFound = proferepo.findByUsuario(newData.getUsuario());

		if (found.isPresent() && !newFound.isPresent()) {
			Profesor temp = found.get();
			temp.setUsuario(newData.getUsuario());
			temp.setContrasenia(newData.getContrasenia());
			proferepo.save(temp);
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
		return proferepo.existsById(id) ? true : false;
	}

	
	public boolean findUsernameAlreadyTaken(Profesor newUser) {
		Optional<Profesor> found = proferepo.findByUsuario(newUser.getUsuario());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
}
