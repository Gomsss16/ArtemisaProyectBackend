package co.edu.unbosque.artemisa.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa un temario dentro del sistema.
 * <p>
 * Se utiliza para transportar la información relacionada con los temas de algoritmos,
 * su tipo, contenido y código asociado, entre las distintas capas de la aplicación.
 * </p>
 */
public class TemarioDTO {

    /**
     * Identificador único del temario.
     */
    private Long id;

    /**
     * Nombre o descripción del tema relacionado con algoritmos.
     */
    private String temaAlgoritmo;

    /**
     * Tipo de contenido del temario (por ejemplo: teoría, práctica, ejemplo, etc.).
     */
    private String tipo;

    /**
     * Contenido textual o explicativo del temario.
     */
    private String contenido;

    /**
     * Fragmento de código relacionado con el temario.
     */
    private String codigo;

    /**
     * Constructor con todos los atributos.
     *
     * @param id           identificador único del temario
     * @param temaAlgoritmo tema o título relacionado con algoritmos
     * @param tipo         tipo de contenido
     * @param contenido    contenido textual o explicativo
     * @param codigo       fragmento de código asociado
     */
    public TemarioDTO(Long id, String temaAlgoritmo, String tipo, String contenido, String codigo) {
        super();
        this.id = id;
        this.temaAlgoritmo = temaAlgoritmo;
        this.tipo = tipo;
        this.contenido = contenido;
        this.codigo = codigo;
    }

    /**
     * Constructor vacío requerido para serialización/deserialización.
     */
    public TemarioDTO() {
        // Constructor por defecto
    }

    /**
     * Obtiene el identificador único del temario.
     *
     * @return id del temario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del temario.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del tema relacionado con algoritmos.
     *
     * @return tema de algoritmo
     */
    public String getTemaAlgoritmo() {
        return temaAlgoritmo;
    }

    /**
     * Establece el nombre del tema relacionado con algoritmos.
     *
     * @param temaAlgoritmo nuevo tema
     */
    public void setTemaAlgoritmo(String temaAlgoritmo) {
        this.temaAlgoritmo = temaAlgoritmo;
    }

    /**
     * Obtiene el tipo de contenido del temario.
     *
     * @return tipo de contenido
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de contenido del temario.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el contenido textual del temario.
     *
     * @return contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido textual del temario.
     *
     * @param contenido nuevo contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el fragmento de código relacionado con el temario.
     *
     * @return código en formato texto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el fragmento de código relacionado con el temario.
     *
     * @param codigo nuevo código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Calcula el hash basado en los atributos del temario.
     *
     * @return valor hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo, contenido, id, temaAlgoritmo, tipo);
    }

    /**
     * Compara si dos objetos TemarioDTO son iguales.
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
        TemarioDTO other = (TemarioDTO) obj;
        return Objects.equals(codigo, other.codigo) && Objects.equals(contenido, other.contenido)
                && Objects.equals(id, other.id) && Objects.equals(temaAlgoritmo, other.temaAlgoritmo)
                && Objects.equals(tipo, other.tipo);
    }

    /**
     * Devuelve una representación en cadena del temario.
     *
     * @return cadena con los datos del temario
     */
    @Override
    public String toString() {
        return "TemarioDTO [id=" + id + ", temaAlgoritmo=" + temaAlgoritmo + ", tipo=" + tipo
                + ", contenido=" + contenido + ", codigo=" + codigo + "]";
    }
}
