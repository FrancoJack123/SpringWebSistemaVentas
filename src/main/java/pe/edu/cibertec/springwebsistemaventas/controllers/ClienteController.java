package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Cliente;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public FindAllResponse<Cliente> ListarClientes(){
        List<Cliente> getList = clienteRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Cliente> BuscarCliente(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty())
            return new FindByIdResponse<>("99", "El cliente buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, cliente.get());
    }

    @PostMapping
    public AddResponse<Cliente> GuardarCliente(@Valid @RequestBody Cliente cliente){
        try {
            Cliente cli = clienteRepository.save(cliente);
            return new AddResponse<>("01", null, cli);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Cliente> ActualizarCliente(@Valid @RequestBody Cliente cliente){
        try {
            if (clienteRepository.findById(cliente.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id cliente no se encuentra en la base de datos", cliente);

            Cliente cli = clienteRepository.save(cliente);
            return new UpdateResponse<>("01", null, cli);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarCliente(@PathVariable Long id){
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (cliente.isEmpty())
                return new DeleteResponse("99", "El id del cliente no existe", "El cliente no fue eliminado");

            clienteRepository.deleteById(id);
            return new DeleteResponse("01", null, "El cliente fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
