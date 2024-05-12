package pe.edu.cibertec.springwebsistemaventas.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.auth.request.LoginRequest;
import pe.edu.cibertec.springwebsistemaventas.auth.request.RegisterRequest;
import pe.edu.cibertec.springwebsistemaventas.auth.response.AuthResponse;
import pe.edu.cibertec.springwebsistemaventas.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authService.register(request));
//    }

}
