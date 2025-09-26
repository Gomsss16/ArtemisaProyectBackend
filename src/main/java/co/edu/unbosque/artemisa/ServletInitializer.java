package co.edu.unbosque.artemisa;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase de inicialización del servlet para despliegues en contenedores externos.
 * <p>
 * Extiende {@link SpringBootServletInitializer} para permitir que la aplicación
 * se ejecute como un archivo WAR dentro de un servidor de aplicaciones
 * (Tomcat, WildFly, GlassFish, etc.).
 * </p>
 *
 * <p>
 * Esta clase solo es necesaria si se empaqueta la aplicación como WAR.
 * Si se ejecuta como JAR independiente, no se utiliza.
 * </p>
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configura la aplicación para ser inicializada dentro de un contenedor de servlets externo.
     *
     * @param application el constructor de la aplicación de Spring Boot
     * @return la aplicación configurada con la clase principal {@link ArtemisaApplication}
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ArtemisaApplication.class);
    }
}
