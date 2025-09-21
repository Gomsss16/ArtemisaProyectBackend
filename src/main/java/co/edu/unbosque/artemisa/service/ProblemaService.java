package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.ProblemaDTO;
import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.entity.Problema;
import co.edu.unbosque.artemisa.entity.Temario;
import co.edu.unbosque.artemisa.repository.ProblemaRepository;


@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO>{

	@Autowired
	private ProblemaRepository probleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(ProblemaDTO data) {
		Problema entity = modelMapper.map(data, Problema.class);
		if (findTituloAlreadyTaken(entity)) {
			return 1;
		} else {
			probleRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<ProblemaDTO> getAll() {
		List<Problema> entityList = probleRepo.findAll();
		List<ProblemaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {

			ProblemaDTO dto = modelMapper.map(entity, ProblemaDTO.class);
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
	public int updateById(Long id, ProblemaDTO newData) {
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
	
	public boolean findTituloAlreadyTaken(Problema newUser) {
		Optional<Problema> found = probleRepo.findByTitulo(newUser.getTitulo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int deleteByTema(String titulo) {
		Optional<Problema> found = probleRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			probleRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
