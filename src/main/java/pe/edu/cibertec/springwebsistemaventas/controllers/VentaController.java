package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Usuario;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ClienteRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.TiendaRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.UsuarioRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.VentaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/venta")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping
    public FindAllResponse<Venta> ListarVentas(){
        List<Venta> getList = ventaRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Venta> BuscarVenta(@PathVariable Long id){
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isEmpty())
            return new FindByIdResponse<>("99", "La venta buscada no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, venta.get());
    }

    @PostMapping
    public AddResponse<Venta> GuardarVenta(@Valid @RequestBody Venta venta){
        try {
            if (tiendaRepository.findById(venta.getTienda().getId()).isEmpty())
                return new AddResponse<>("99", "La tienda especificada no existe", null);

            if (usuarioRepository.findById(venta.getUsuario().getId()).isEmpty())
                return new AddResponse<>("99", "El usuario especificado no existe", null);

            if (clienteRepository.findById(venta.getCliente().getId()).isEmpty())
                return new AddResponse<>("99", "El cliente especificado no existe", null);

            Venta va = ventaRepository.save(venta);
            return new AddResponse<>("01", "La venta fue registrada", null);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Venta> ActualizarVenta(@Valid @RequestBody Venta venta){
        try {
            if (ventaRepository.findById(venta.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id de la venta no se encuentra en la base de datos", null);

            if (tiendaRepository.findById(venta.getTienda().getId()).isEmpty())
                return new UpdateResponse<>("01", "La tienda especificada no existe", null);

            if (usuarioRepository.findById(venta.getUsuario().getId()).isEmpty())
                return new UpdateResponse<>("01", "El usuario especificado no existe", null);

            if (clienteRepository.findById(venta.getCliente().getId()).isEmpty())
                return new UpdateResponse<>("01", "El cliente especificado no existe", null);

            Venta va = ventaRepository.save(venta);
            return new UpdateResponse<>("01", "La venta fue actualizada", null);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarVenta(@PathVariable Long id){
        try{
            Optional<Venta> venta = ventaRepository.findById(id);

            if (venta.isEmpty())
                return new DeleteResponse("99", "El id de la venta no existe", "La venta no fue eliminada");

            ventaRepository.deleteById(id);
            return new DeleteResponse("01", null, "La venta fue eliminada");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
