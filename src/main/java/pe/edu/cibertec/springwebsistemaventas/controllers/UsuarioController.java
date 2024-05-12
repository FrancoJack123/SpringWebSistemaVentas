package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Rol;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Usuario;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public FindAllResponse<Usuario> ListarUsuarios(){
        List<Usuario> getList = usuarioRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Usuario> BuscarUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty())
            return new FindByIdResponse<>("99", "El usuario buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, usuario.get());
    }

    @PostMapping("/admin")
    public AddResponse<Usuario> GuardarUsuarioAdmin(@Valid @RequestBody Usuario usuario){
        try {
            usuario.setRol(Rol.ADMIN);
            Usuario user = usuarioRepository.save(usuario);
            return new AddResponse<>("01", null, user);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PostMapping("/user")
    public AddResponse<Usuario> GuardarUsuarioUser(@Valid @RequestBody Usuario usuario){
        try {
            usuario.setRol(Rol.USER);
            Usuario user = usuarioRepository.save(usuario);
            return new AddResponse<>("01", null, user);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Usuario> ActualizarUsuario(@Valid @RequestBody Usuario usuario){
        try {
            if (usuarioRepository.findById(usuario.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id usuario no se encuentra en la base de datos", usuario);

            Usuario usu = usuarioRepository.save(usuario);
            return new UpdateResponse<>("01", null, usu);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarUsuario(@PathVariable Long id){
        try{
            Optional<Usuario> usuario = usuarioRepository.findById(id);

            if (usuario.isEmpty())
                return new DeleteResponse("99", "El id del usuario no existe", "El usuario no fue eliminado");

            if (usuario.get().getRol() == Rol.ADMIN)
                return new DeleteResponse("98", null, "El usuario admin no se puede eliminar");

            usuarioRepository.deleteById(id);
            return new DeleteResponse("01", null, "El usuario fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
