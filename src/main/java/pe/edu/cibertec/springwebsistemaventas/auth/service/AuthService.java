package pe.edu.cibertec.springwebsistemaventas.auth.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.auth.request.LoginRequest;
import pe.edu.cibertec.springwebsistemaventas.auth.request.RegisterRequest;
import pe.edu.cibertec.springwebsistemaventas.auth.response.AuthResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.cibertec.springwebsistemaventas.jwt.JwtService;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Rol;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Usuario;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.USER)
                .build();

        usuarioRepository.save(user);

        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
