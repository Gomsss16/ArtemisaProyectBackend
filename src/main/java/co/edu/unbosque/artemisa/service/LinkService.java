package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LinkDTO;
import co.edu.unbosque.artemisa.entity.Link;
import co.edu.unbosque.artemisa.repository.LinkRepository;

@Service
public class LinkService implements CRUDOperation<LinkDTO> {

	@Autowired
	private LinkRepository linkRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(LinkDTO data) {
		Link entity = modelMapper.map(data, Link.class);
		if (findLinkAlreadyTaken(entity)) { // ← NOMBRE CORREGIDO
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
		return 0;
	}

	@Override
	public int updateById(Long id, LinkDTO newData) {
		return 0;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public boolean exist(Long id) {
		return false;
	}

	public boolean findLinkAlreadyTaken(Link newLink) { // ← NOMBRE CORREGIDO
		Optional<Link> found = linkRepo.findByTitulo(newLink.getTitulo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	// ← MÉTODO FALTANTE
	public int deleteByTitle(String titulo) {
		Optional<Link> found = linkRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			linkRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
