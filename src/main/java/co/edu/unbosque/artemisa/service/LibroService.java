package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.entity.Libro;
import co.edu.unbosque.artemisa.repository.LibroRepository;

@Service
public class LibroService implements CRUDOperation<LibroDTO> {

	@Autowired
	private LibroRepository libroRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(LibroDTO data) {
		Libro entity = modelMapper.map(data, Libro.class);
		if (findlibroAlreadyTaken(entity)) {
			return 1;
		} else {
			libroRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<LibroDTO> getAll() {
		List<Libro> entityList = libroRepo.findAll();
		List<LibroDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			LibroDTO dto = modelMapper.map(entity, LibroDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		return 0;
	}

	@Override
	public int updateById(Long id, LibroDTO newData) {
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

	public boolean findlibroAlreadyTaken(Libro newLibro) {
		Optional<Libro> found = libroRepo.findByTitulo(newLibro.getTitulo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public int deleteByTitle(String titulo) {
		Optional<Libro> found = libroRepo.findByTitulo(titulo);
		if (found.isPresent()) {
			libroRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
}
