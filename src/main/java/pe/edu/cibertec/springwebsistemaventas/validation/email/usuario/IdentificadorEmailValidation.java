package pe.edu.cibertec.springwebsistemaventas.validation.email.usuario;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.UsuarioRepository;

public class IdentificadorEmailValidation implements ConstraintValidator<EmailValidator, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (usuarioRepository.existsByEmailContainsIgnoreCase(s))
            return false;
        return true;
    }
}
