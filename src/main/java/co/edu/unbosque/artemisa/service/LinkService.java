package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.Base64;
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
        try {
            Link entity = new Link();
            entity.setTitulo(data.getTitulo());
            entity.setDescripcion(data.getDescripcion());
            entity.setEnlace(data.getEnlace());

            // 👇 Convertir Base64 -> byte[]
            if (data.getImagenBase64() != null && !data.getImagenBase64().isEmpty()) {
                String base64Data = data.getImagenBase64();
                // Si viene con encabezado "data:image/png;base64,..." lo limpio
                if (base64Data.contains(",")) {
                    base64Data = base64Data.split(",")[1];
                }
                entity.setImagen(Base64.getDecoder().decode(base64Data));
            }

            if (findLinkAlreadyTaken(entity)) {
                return 1; // ya existe
            }

            linkRepo.save(entity);
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 2; // error
        }
    }

    @Override
    public List<LinkDTO> getAll() {
        List<Link> entityList = linkRepo.findAll();
        List<LinkDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            LinkDTO dto = modelMapper.map(entity, LinkDTO.class);

            if (entity.getImagen() != null) {
                String base64 = Base64.getEncoder().encodeToString(entity.getImagen());
                dto.setImagenBase64("data:image/png;base64," + base64);
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
    public int updateById(Long id, LinkDTO newData) {
        return 0;
    }

    @Override
    public long count() {
        return linkRepo.count();
    }

    @Override
    public boolean exist(Long id) {
        return linkRepo.existsById(id);
    }

    public boolean findLinkAlreadyTaken(Link newLink) {
        Optional<Link> found = linkRepo.findByTitulo(newLink.getTitulo());
        return found.isPresent();
    }

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
