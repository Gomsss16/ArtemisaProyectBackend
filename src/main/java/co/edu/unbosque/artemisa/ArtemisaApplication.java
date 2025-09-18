package co.edu.unbosque.artemisa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArtemisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemisaApplication.class, args);
	}

	

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	// https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
}
