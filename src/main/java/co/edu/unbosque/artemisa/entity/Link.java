package co.edu.unbosque.artemisa.entity;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Entidad que representa un enlace (link) dentro del sistema.
 * <p>
 * Cada link contiene un título único, una descripción, un enlace externo y
 * opcionalmente una imagen asociada.
 * </p>
 */
@Entity
@Table(name = "link")
public class Link {

	/**
	 * Identificador único autogenerado para cada link.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Título único del link.
	 */
	@Column(unique = true)
	private String titulo;

	/**
	 * Descripción detallada del link.
	 */
	@Column(columnDefinition = "TEXT")
	private String descripcion;

	/**
	 * URL o enlace externo asociado.
	 */
	private String enlace;

	/**
	 * Imagen relacionada con el link (almacenada en formato binario).
	 */
	@Lob
	@Column(name = "imagen", columnDefinition = "LONGBLOB")
	private byte[] imagen;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Link() {
	}

	/**
	 * Constructor completo para inicializar un link.
	 *
	 * @param id          Identificador único
	 * @param titulo      Título del link
	 * @param descripcion Descripción del link
	 * @param enlace      Enlace externo
	 * @param imagen      Imagen en bytes
	 */
	public Link(Long id, String titulo, String descripcion, String enlace, byte[] imagen) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.imagen = imagen;
	}

	/**
	 * Genera un valor hash basado en los atributos de la clase.
	 *
	 * @return hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, enlace, id, titulo) + Arrays.hashCode(imagen);
	}

	/**
	 * Compara si dos objetos Link son iguales.
	 *
	 * @param obj Objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Link other))
			return false;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(enlace, other.enlace)
				&& Arrays.equals(imagen, other.imagen);
	}


	/**
	 * Obtiene el id del link.
	 *
	 * @return id del link
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna el id del link.
	 *
	 * @param id nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el título del link.
	 *
	 * @return título
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Asigna el título del link.
	 *
	 * @param titulo nuevo título
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción del link.
	 *
	 * @return descripción
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripción del link.
	 *
	 * @param descripcion nueva descripción
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el enlace externo.
	 *
	 * @return enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * Asigna el enlace externo.
	 *
	 * @param enlace nuevo enlace
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Obtiene la imagen en bytes.
	 *
	 * @return imagen en formato byte[]
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * Asigna la imagen en bytes.
	 *
	 * @param imagen nueva imagen en formato byte[]
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * Representación en texto del objeto Link.
	 *
	 * @return cadena con los atributos principales
	 */
	@Override
	public String toString() {
		return "Link{id=" + id + ", titulo='" + titulo + '\'' + ", descripcion='" + descripcion + '\'' + ", enlace='"
				+ enlace + '\'' + ", imagen=" + (imagen != null ? imagen.length + " bytes" : "null") + '}';
	}
}
