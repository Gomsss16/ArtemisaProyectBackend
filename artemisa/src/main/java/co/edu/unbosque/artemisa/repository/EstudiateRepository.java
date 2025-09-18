package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Estudiante;

public interface EstudiateRepository extends JpaRepository<Estudiante, Long>{
	public Optional<Estudiante> findByUsuario(String usuario);
	public void deleteByUsuario(String usuario);
	public boolean existsByUsuario(String usuario);

}
