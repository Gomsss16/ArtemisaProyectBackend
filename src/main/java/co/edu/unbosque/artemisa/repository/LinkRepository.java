package co.edu.unbosque.artemisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long>{
	public Optional<Link> findByTitulo(String titulo);

	public boolean existsByTitulo(String titulo);

}
