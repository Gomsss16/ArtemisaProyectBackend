package co.edu.unbosque.artemisa.entity;

import java.util.Objects;

import jakarta.persistence.*;

/**
 * Entidad que representa un problema de programación en el sistema.
 * <p>
 * Cada problema contiene un título único, una dificultad asociada, un tema
 * categórico, el juez en el cual está publicado y un enlace de referencia para
 * acceder al enunciado o resolución.
 * </p>
 */
@Entity
@Table(name = "problema")
public class Problema {

	/**
	 * Identificador único autogenerado del problema.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Título único del problema.
	 */
	@Column(unique = true)
	private String titulo;

	/**
	 * Nivel de dificultad del problema (ej: escala numérica).
	 */
	private int dificultad;

	/**
	 * Tema asociado al problema (ej: grafos, recursividad, etc.).
	 */
	private String tema;

	/**
	 * Juez en línea en el cual está publicado (ej: Codeforces, UVA).
	 */
	private String juez;

	/**
	 * Enlace web al enunciado o a la fuente del problema.
	 */
	private String link;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Problema() {
	}

	/**
	 * Constructor completo para inicializar un problema.
	 *
	 * @param id         Identificador único
	 * @param titulo     Título del problema
	 * @param dificultad Nivel de dificultad
	 * @param tema       Tema del problema
	 * @param juez       Juez en el que está publicado
	 * @param link       Enlace del problema
	 */
	public Problema(Long id, String titulo, int dificultad, String tema, String juez, String link) {
		this.id = id;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
		this.link = link;
	}

	/**
	 * Genera un valor hash basado en los atributos de la clase.
	 *
	 * @return hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, juez, link, tema, titulo);
	}

	/**
	 * Compara si dos objetos Problema son iguales.
	 *
	 * @param obj Objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Problema other))
			return false;
		return dificultad == other.dificultad && Objects.equals(id, other.id) && Objects.equals(juez, other.juez)
				&& Objects.equals(link, other.link) && Objects.equals(tema, other.tema)
				&& Objects.equals(titulo, other.titulo);
	}


	/**
	 * Obtiene el identificador del problema.
	 *
	 * @return id del problema
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna el identificador del problema.
	 *
	 * @param id nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del problema.
	 *
	 * @return título del problema
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Asigna el título del problema.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la dificultad del problema.
	 *
	 * @return dificultad en valor numérico
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * Asigna la dificultad del problema.
	 *
	 * @param dificultad nueva dificultad
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Obtiene el tema asociado al problema.
	 *
	 * @return tema del problema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Asigna el tema del problema.
	 *
	 * @param tema nuevo tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Obtiene el juez en el que está publicado.
	 *
	 * @return juez en línea
	 */
	public String getJuez() {
		return juez;
	}

	/**
	 * Asigna el juez en el que está publicado.
	 *
	 * @param juez nuevo juez
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * Obtiene el enlace del problema.
	 *
	 * @return enlace web
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Asigna el enlace del problema.
	 *
	 * @param link nuevo enlace web
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Representación en texto del objeto Problema.
	 *
	 * @return cadena con los atributos principales
	 */
	@Override
	public String toString() {
		return "Problema{" + "id=" + id + ", titulo='" + titulo + '\'' + ", dificultad=" + dificultad + ", tema='"
				+ tema + '\'' + ", juez='" + juez + '\'' + ", link='" + link + '\'' + '}';
	}
}
