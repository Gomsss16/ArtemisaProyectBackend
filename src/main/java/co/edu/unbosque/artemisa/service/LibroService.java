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
import java.util.Base64;

@Service
public class LibroService implements CRUDOperation<LibroDTO> {

	@Autowired
	private LibroRepository libroRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int create(LibroDTO data) {
	    Libro entity = new Libro();
	    entity.setTitulo(data.getTitulo());
	    entity.setAuthor(data.getAuthor());
	    entity.setDescripcion(data.getDescripcion());

	    if (data.getPdfBase64() != null && !data.getPdfBase64().isEmpty()) {
	        entity.setPdf(Base64.getDecoder().decode(data.getPdfBase64()));
	    }

	    if (data.getImagenBase64() != null && !data.getImagenBase64().isEmpty()) {
	        entity.setImagen(Base64.getDecoder().decode(data.getImagenBase64()));
	    }

	    if (findlibroAlreadyTaken(entity)) {
	        return 1;
	    }

	    libroRepo.save(entity);
	    return 0;
	}
	
	@Override
	public List<LibroDTO> getAll() {
	    List<Libro> entityList = libroRepo.findAll();
	    List<LibroDTO> dtoList = new ArrayList<>();

	    entityList.forEach((entity) -> {
	        LibroDTO dto = new LibroDTO();
	        dto.setId(entity.getId());
	        dto.setTitulo(entity.getTitulo());
	        dto.setAuthor(entity.getAuthor());
	        dto.setDescripcion(entity.getDescripcion());

	        // Codificar byte[] a Base64 para enviar al front
	        if (entity.getPdf() != null) {
	            dto.setPdfBase64(Base64.getEncoder().encodeToString(entity.getPdf()));
	        }

	        if (entity.getImagen() != null) {
	            dto.setImagenBase64(Base64.getEncoder().encodeToString(entity.getImagen()));
	        }

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
