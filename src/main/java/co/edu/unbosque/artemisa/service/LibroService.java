package co.edu.unbosque.artemisa.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.LibroDTO;
import co.edu.unbosque.artemisa.entity.Libro;
import co.edu.unbosque.artemisa.repository.LibroRepository;


@Service
public class LibroService implements CRUDOperation<LibroDTO>{
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, LibroDTO newData) {
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
	
	public boolean findlibroAlreadyTaken(Libro newUser) {
		Optional<Libro> found = libroRepo.findByTitulo(newUser.getTitulo());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
