package co.edu.unbosque.artemisa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "temario")
public class Temario {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique=true)
	private String temaAlgoritmo;
	private String tipo;
	
	@Lob 
	@Column(columnDefinition = "TEXT")
	private String contenido;
	
	@Lob 
	@Column(columnDefinition = "TEXT")
	private String codigo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemaAlgoritmo() {
		return temaAlgoritmo;
	}

	public void setTemaAlgoritmo(String temaAlgoritmo) {
		this.temaAlgoritmo = temaAlgoritmo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, contenido, id, temaAlgoritmo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temario other = (Temario) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(contenido, other.contenido)
				&& Objects.equals(id, other.id) && Objects.equals(temaAlgoritmo, other.temaAlgoritmo)
				&& Objects.equals(tipo, other.tipo);
	}
	
	public Temario() {
		// TODO Auto-generated constructor stub
	}

	public Temario(Long id, String temaAlgoritmo, String tipo, String contenido, String codigo) {
		super();
		this.id = id;
		this.temaAlgoritmo = temaAlgoritmo;
		this.tipo = tipo;
		this.contenido = contenido;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Temario [id=" + id + ", temaAlgoritmo=" + temaAlgoritmo + ", tipo=" + tipo + ", contenido=" + contenido
				+ ", codigo=" + codigo + "]";
	}
	
	
	
	
	
	

}
