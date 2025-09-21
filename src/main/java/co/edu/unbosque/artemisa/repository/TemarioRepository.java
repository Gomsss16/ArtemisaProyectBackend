package co.edu.unbosque.artemisa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.artemisa.entity.Temario;

public interface TemarioRepository extends JpaRepository<Temario, Long>{
	public Optional<Temario> findByTemaAlgoritmo(String temaAlgoritmo);
	public void deleteByTemaAlgoritmo(String temaAlgoritmo);
	public boolean existsByTemaAlgoritmo(String temaAlgoritmo);
	public List<Temario> findByTipo(String tipo);
	
	

}
