package co.edu.unbosque.artemisa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
	public Optional<Administrador> findByUsuario(String usuario);
	public void deleteByUsuario(String usuario);
	public boolean existsByUsuario(String usuario);

}
