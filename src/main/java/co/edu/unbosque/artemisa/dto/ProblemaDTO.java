package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa un problema de programación o
 * ejercicio.
 * <p>
 * Se utiliza para transportar información de problemas entre las diferentes
 * capas de la aplicación, sin exponer directamente la entidad de persistencia.
 * Incluye título, dificultad, tema, juez asociado y enlace.
 * </p>
 */
public class ProblemaDTO {

	/**
	 * Identificador único del problema.
	 */
	private Long id;

	/**
	 * Título del problema.
	 */
	private String titulo;

	/**
	 * Nivel de dificultad del problema. Se representa como un valor entero (ej. 1 =
	 * fácil, 2 = intermedio, 3 = difícil).
	 */
	private int dificultad;

	/**
	 * Tema o categoría al que pertenece el problema.
	 */
	private String tema;

	/**
	 * Juez en línea o plataforma que aloja el problema (ejemplo: Codeforces, UVA,
	 * LeetCode).
	 */
	private String juez;

	/**
	 * Enlace al problema en el juez correspondiente.
	 */
	private String link;

	/**
	 * Constructor vacío requerido para serialización y deserialización.
	 */
	public ProblemaDTO() {
		// Constructor por defecto
	}

	/**
	 * Constructor con todos los campos.
	 *
	 * @param id         identificador único
	 * @param titulo     título del problema
	 * @param dificultad nivel de dificultad (ej. 1, 2, 3)
	 * @param tema       tema o categoría
	 * @param juez       plataforma o juez en línea
	 * @param link       URL del problema
	 */
	public ProblemaDTO(Long id, String titulo, int dificultad, String tema, String juez, String link) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
		this.link = link;
	}

	/**
	 * Calcula el hash basado en los atributos del problema.
	 *
	 * @return valor hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, juez, link, tema, titulo);
	}

	/**
	 * Compara si dos objetos ProblemaDTO son iguales.
	 *
	 * @param obj objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaDTO other = (ProblemaDTO) obj;
		return dificultad == other.dificultad && Objects.equals(id, other.id) && Objects.equals(juez, other.juez)
				&& Objects.equals(link, other.link) && Objects.equals(tema, other.tema)
				&& Objects.equals(titulo, other.titulo);
	}

	/**
	 * Obtiene el identificador único del problema.
	 *
	 * @return id del problema
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del problema.
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
	 * Establece el título del problema.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene el nivel de dificultad del problema.
	 *
	 * @return dificultad como número entero
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * Establece el nivel de dificultad del problema.
	 *
	 * @param dificultad nuevo nivel de dificultad
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Obtiene el tema o categoría del problema.
	 *
	 * @return tema del problema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Establece el tema o categoría del problema.
	 *
	 * @param tema nuevo tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Obtiene el juez o plataforma en línea donde está publicado el problema.
	 *
	 * @return nombre del juez
	 */
	public String getJuez() {
		return juez;
	}

	/**
	 * Establece el juez o plataforma del problema.
	 *
	 * @param juez nuevo juez
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * Obtiene el enlace del problema en el juez.
	 *
	 * @return URL del problema
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Establece el enlace del problema en el juez.
	 *
	 * @param link nueva URL
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Devuelve una representación en cadena del problema.
	 *
	 * @return cadena con los datos del problema
	 */
	@Override
	public String toString() {
		return "ProblemaDTO [id=" + id + ", titulo=" + titulo + ", dificultad=" + dificultad + ", tema=" + tema
				+ ", juez=" + juez + ", link=" + link + "]";
	}
}
