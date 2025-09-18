package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "id")
public class Profesor extends Usuario {
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}


	
	public Profesor(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return super.toString() + "Profesor : ";
	}

}
