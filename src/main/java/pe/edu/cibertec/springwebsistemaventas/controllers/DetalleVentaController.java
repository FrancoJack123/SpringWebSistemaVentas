package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.DetalleVenta;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.DetalleVentaRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/detalleventa")
public class DetalleVentaController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @GetMapping
    public FindAllResponse<DetalleVenta> ListarDetalleVentas(){
        List<DetalleVenta> getList = detalleVentaRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<DetalleVenta> BuscarDetalleVentas(@PathVariable Long id){
        Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id);
        if (detalleVenta.isEmpty())
            return new FindByIdResponse<>("99", "El detalleVenta buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, detalleVenta.get());
    }

    @PostMapping
    public AddResponse<DetalleVenta> GuardarDetalleVentas(@Valid @RequestBody DetalleVenta detalleVenta){
        try {
            if (productoRepository.findById(detalleVenta.getProducto().getId()).isEmpty())
                return new AddResponse<>("99", "El producto especificado no existe", null);

            DetalleVenta de = detalleVentaRepository.save(detalleVenta);
            return new AddResponse<>("01", null, de);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<DetalleVenta> ActualizarDetalleVentas(@Valid @RequestBody DetalleVenta detalleVenta){
        try {
            if (detalleVentaRepository.findById(detalleVenta.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id detalleVenta no se encuentra en la base de datos", detalleVenta);

            if (productoRepository.findById(detalleVenta.getProducto().getId()).isEmpty())
                return new UpdateResponse<>("99", "El producto especificado no existe", null);

            DetalleVenta de = detalleVentaRepository.save(detalleVenta);
            return new UpdateResponse<>("01", null, de);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarDetalleVentas(@PathVariable Long id){
        try{
            Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id);

            if (detalleVenta.isEmpty())
                return new DeleteResponse("99", "El id del detalleVenta no existe", "El detalleVenta no fue eliminado");

            detalleVentaRepository.deleteById(id);
            return new DeleteResponse("01", null, "El detalleVenta fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
