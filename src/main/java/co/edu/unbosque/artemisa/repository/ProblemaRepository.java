package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Problema;


public interface ProblemaRepository extends JpaRepository<Problema, Long>{
	
	public Optional<Problema> findByTitulo(String titulo);

	public boolean existsByTitulo(String titulo);

}
