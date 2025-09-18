package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long>{
	public Optional<Profesor> findByUsuario(String usuario);
	public void deleteByUsuario(String usuario);
	public boolean existsByUsuario(String usuario);
}
