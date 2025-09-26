package co.edu.unbosque.artemisa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Entidad que representa un temario en el sistema.
 * <p>
 * Contiene información sobre temas de algoritmos, su tipo, el contenido
 * explicativo y el código asociado.
 * </p>
 */
@Entity
@Table(name = "temario")
public class Temario {

    /**
     * Identificador único del temario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tema principal del algoritmo.
     */
    @Column(unique = true)
    private String temaAlgoritmo;

    /**
     * Tipo o categoría del tema.
     */
    private String tipo;

    /**
     * Contenido descriptivo del tema.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String contenido;

    /**
     * Ejemplo de código relacionado con el tema.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String codigo;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Temario() {
        // Constructor por defecto
    }

    /**
     * Constructor para inicializar todos los atributos del temario.
     *
     * @param id            identificador único
     * @param temaAlgoritmo nombre del tema del algoritmo
     * @param tipo          categoría del tema
     * @param contenido     explicación o desarrollo teórico
     * @param codigo        código de ejemplo asociado
     */
    public Temario(Long id, String temaAlgoritmo, String tipo, String contenido, String codigo) {
        super();
        this.id = id;
        this.temaAlgoritmo = temaAlgoritmo;
        this.tipo = tipo;
        this.contenido = contenido;
        this.codigo = codigo;
    }

    /**
     * Obtiene el identificador del temario.
     *
     * @return id del temario
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador del temario.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el tema del algoritmo.
     *
     * @return tema del algoritmo
     */
    public String getTemaAlgoritmo() {
        return temaAlgoritmo;
    }

    /**
     * Establece el tema del algoritmo.
     *
     * @param temaAlgoritmo nuevo tema
     */
    public void setTemaAlgoritmo(String temaAlgoritmo) {
        this.temaAlgoritmo = temaAlgoritmo;
    }

    /**
     * Obtiene el tipo o categoría del tema.
     *
     * @return tipo del tema
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo o categoría del tema.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el contenido explicativo del tema.
     *
     * @return contenido en formato texto
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Asigna el contenido explicativo del tema.
     *
     * @param contenido nuevo contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el código de ejemplo asociado al tema.
     *
     * @return código en formato texto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de ejemplo del tema.
     *
     * @param codigo nuevo código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Genera el hash del objeto.
     *
     * @return valor hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo, contenido, id, temaAlgoritmo, tipo);
    }

    /**
     * Compara si dos objetos Temario son iguales.
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
        Temario other = (Temario) obj;
        return Objects.equals(codigo, other.codigo) && Objects.equals(contenido, other.contenido)
                && Objects.equals(id, other.id) && Objects.equals(temaAlgoritmo, other.temaAlgoritmo)
                && Objects.equals(tipo, other.tipo);
    }

    /**
     * Representación en texto del objeto Temario.
     *
     * @return cadena con los valores de los atributos
     */
    @Override
    public String toString() {
        return "Temario [id=" + id + ", temaAlgoritmo=" + temaAlgoritmo + ", tipo=" + tipo + ", contenido=" + contenido
                + ", codigo=" + codigo + "]";
    }
}
