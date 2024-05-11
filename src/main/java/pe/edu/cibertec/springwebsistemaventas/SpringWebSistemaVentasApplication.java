package pe.edu.cibertec.springwebsistemaventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Rol;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Usuario;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.UsuarioRepository;

@SpringBootApplication
public class SpringWebSistemaVentasApplication implements CommandLineRunner{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebSistemaVentasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByEmail("admin@gmai.com").isEmpty()) {
            Usuario usuario = Usuario.builder()
                    .rol(Rol.ADMIN)
                    .nombres("Admin")
                    .apellidos("Mor")
                    .email("admin@gmai.com")
                    .password(passwordEncoder.encode("123"))
                    .build();
            usuarioRepository.save(usuario);
        }

        if (usuarioRepository.findByEmail("user@gmai.com").isEmpty()) {
            Usuario usuario = Usuario.builder()
                    .rol(Rol.USER)
                    .nombres("User")
                    .apellidos("User")
                    .email("user@gmai.com")
                    .password(passwordEncoder.encode("123"))
                    .build();
            usuarioRepository.save(usuario);
        }
    }
}
