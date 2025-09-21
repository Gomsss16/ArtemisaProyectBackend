package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.entity.Temario;
import co.edu.unbosque.artemisa.repository.TemarioRepository;

@Service
public class TemarioService implements CRUDOperation<TemarioDTO> {

	@Autowired
	private TemarioRepository temaRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(TemarioDTO data) {
		Temario entity = modelMapper.map(data, Temario.class);
		if (findtemaAlreadyTaken(entity)) {
			return 1;
		} else {
			temaRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<TemarioDTO> getAll() {
		List<Temario> entityList = temaRepo.findAll();
		List<TemarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {

			TemarioDTO dto = modelMapper.map(entity, TemarioDTO.class);
			dtoList.add(dto);
		});

		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, TemarioDTO newData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean findtemaAlreadyTaken(Temario newUser) {
		Optional<Temario> found = temaRepo.findByTemaAlgoritmo(newUser.getTemaAlgoritmo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public List<TemarioDTO> getByTipo(String tipo) {
		List<Temario> entities = temaRepo.findByTipo(tipo);

		return entities.stream()
				.map(e -> new TemarioDTO(e.getId(), e.getTemaAlgoritmo(), e.getTipo(), e.getContenido(), e.getCodigo()))
				.toList();
	}
	
	public int deleteByTema(String tema) {
		Optional<Temario> found = temaRepo.findByTemaAlgoritmo(tema);
		if (found.isPresent()) {
			temaRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

}
