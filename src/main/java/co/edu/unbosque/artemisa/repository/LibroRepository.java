package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	public Optional<Libro> findByTitulo(String titulo);

	public boolean existsByTitulo(String titulo);

}
