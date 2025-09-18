package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiantes")
@PrimaryKeyJoinColumn(name = "id")
public class Estudiante extends Usuario {

	public Estudiante() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Estudiante(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
	}



	@Override
	public String toString() {
		return super.toString() + "Estudiantes : ";
	}
	
}
