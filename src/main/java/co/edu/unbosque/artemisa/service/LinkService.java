package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LinkDTO;
import co.edu.unbosque.artemisa.dto.TemarioDTO;
import co.edu.unbosque.artemisa.entity.Link;
import co.edu.unbosque.artemisa.entity.Temario;
import co.edu.unbosque.artemisa.repository.LinkRepository;
import co.edu.unbosque.artemisa.repository.TemarioRepository;

@Service
public class LinkService implements CRUDOperation<LinkDTO>{
	
	@Autowired
	private LinkRepository linkRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(LinkDTO data) {
		Link entity = modelMapper.map(data, Link.class);
		if (findtemaAlreadyTaken(entity)) {
			return 1;
		} else {
			linkRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<LinkDTO> getAll() {
		List<Link> entityList = linkRepo.findAll();
		List<LinkDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {

			LinkDTO dto = modelMapper.map(entity, LinkDTO.class);
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
	public int updateById(Long id, LinkDTO newData) {
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
	
	public boolean findtemaAlreadyTaken(Link newUser) {
		Optional<Link> found = linkRepo.findByTitulo(newUser.getTitulo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
