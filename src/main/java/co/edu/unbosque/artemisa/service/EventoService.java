package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.EventoDTO;
import co.edu.unbosque.artemisa.entity.Evento;
import co.edu.unbosque.artemisa.repository.EventoRepository;

@Service
public class EventoService implements CRUDOperation<EventoDTO> {

    @Autowired
    private EventoRepository eventoRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int create(EventoDTO data) {
        Evento entity = modelMapper.map(data, Evento.class);
        if (findEventoAlreadyTaken(entity)) {
            return 1;
        } else {
            eventoRepo.save(entity);
            return 0;
        }
    }

    @Override
    public List<EventoDTO> getAll() {
        List<Evento> entityList = eventoRepo.findAll();
        List<EventoDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            EventoDTO dto = modelMapper.map(entity, EventoDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int updateById(Long id, EventoDTO newData) {
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

    public boolean findEventoAlreadyTaken(Evento newEvento) {
        Optional<Evento> found = eventoRepo.findByTitulo(newEvento.getTitulo());
        if (found.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public int deleteByTitle(String titulo) {
        Optional<Evento> found = eventoRepo.findByTitulo(titulo);
        if (found.isPresent()) {
            eventoRepo.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }
}
