package co.edu.unbosque.artemisa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.artemisa.dto.AdministradorDTO;
import co.edu.unbosque.artemisa.entity.Administrador;
import co.edu.unbosque.artemisa.repository.AdministradorRepository;


@Service
public class AdministradorService implements CRUDOperation<AdministradorDTO> {

	@Autowired
	private AdministradorRepository adminrepo;
	@Autowired
	private ModelMapper modelMapper;

	public AdministradorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(AdministradorDTO data) {
		Administrador entity = modelMapper.map(data, Administrador.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		} else {
			adminrepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<AdministradorDTO> getAll() {
		List<Administrador> entityList = adminrepo.findAll();
		List<AdministradorDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			AdministradorDTO dto = modelMapper.map(entity, AdministradorDTO.class);
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
	public int updateById(Long id, AdministradorDTO newData) {
		Optional<Administrador> found = adminrepo.findById(id);
		Optional<Administrador> newFound = adminrepo.findByUsuario(newData.getUsuario());

		if (found.isPresent() && !newFound.isPresent()) {
			Administrador temp = found.get();
			temp.setUsuario(newData.getUsuario());
			temp.setContrasenia(newData.getContrasenia());
			adminrepo.save(temp);
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
		return adminrepo.existsById(id) ? true : false;
	}

	public boolean findUsernameAlreadyTaken(Administrador newUser) {
		Optional<Administrador> found = adminrepo.findByUsuario(newUser.getUsuario());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
