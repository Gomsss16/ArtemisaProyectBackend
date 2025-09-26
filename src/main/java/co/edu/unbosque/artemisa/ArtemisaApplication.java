package co.edu.unbosque.artemisa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Clase principal de la aplicación Artemisa.
 * <p>
 * Utiliza la anotación {@link SpringBootApplication} para habilitar la
 * configuración automática, el escaneo de componentes y la inicialización del
 * contexto de Spring.
 * </p>
 *
 * <p>
 * Esta clase contiene el método main, punto de entrada de la aplicación, y
 * define beans adicionales para el contenedor de Spring, como el
 * {@link ModelMapper}.
 * </p>
 */
@SpringBootApplication
public class ArtemisaApplication {

	/**
	 * Método principal que arranca la aplicación Spring Boot.
	 *
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(ArtemisaApplication.class, args);
	}

	/**
	 * Bean de configuración para {@link ModelMapper}.
	 * <p>
	 * ModelMapper se utiliza para mapear automáticamente entidades a DTOs y
	 * viceversa, simplificando la conversión de objetos en las capas de la
	 * aplicación.
	 * </p>
	 *
	 * @return una instancia de {@link ModelMapper} gestionada por Spring
	 */
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
