package co.edu.unbosque.artemisa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id") 
public class Administrador extends Usuario{
	
	public Administrador() {
		// TODO Auto-generated constructor stub
	}


	


	public Administrador(Long id, String usuario, String contrasenia, String nivelDePermiso, String fechaDeNacimiento) {
		super(id, usuario, contrasenia, nivelDePermiso, fechaDeNacimiento);
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return super.toString() + "Administrador : ";
	}


}
