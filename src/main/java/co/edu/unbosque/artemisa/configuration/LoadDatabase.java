//package co.edu.unbosque.artemisa.configuration;
//
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import co.edu.unbosque.artemisa.entity.Administrador;
//import co.edu.unbosque.artemisa.repository.AdministradorRepository;
//import co.edu.unbosque.artemisa.util.AESUtil;
//
//@Configuration
//public class LoadDatabase {
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//	@Bean
//	CommandLineRunner initDatabase(AdministradorRepository admiRepo) {
//
//		return args -> {
//			 Optional<Administrador> found = admiRepo.findByUsuario(AESUtil.encrypt("admin"));
////			Optional<Administrador> found = admiRepo.findByUsuario("admin");
//			if (found.isPresent()) {
//				log.info("Admin already exists,  skipping admin creating  ...");
//			} else {
//				admiRepo.save(new Administrador(null, AESUtil.encrypt("admin"),AESUtil.encrypt("1234567890"), AESUtil.encrypt("admin"), AESUtil.encrypt("2005")));
////				admiRepo.save(new Administrador());
//				log.info("Preloading admin user");
//			}
//		};
//	}
//
//}
