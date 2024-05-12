package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.DetalleCompra;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.DetalleVenta;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.CompraRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.DetalleCompraRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/detalle-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public FindAllResponse<DetalleCompra> ListarDetalleCompras(){
        List<DetalleCompra> getList = detalleCompraRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<DetalleCompra> BuscarDetalleCompra(@PathVariable Long id){
        Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty())
            return new FindByIdResponse<>("99", "El detalleCompra buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, detalleCompra.get());
    }

    @PostMapping
    public AddResponse<DetalleCompra> GuardarDetalleCompra(@Valid @RequestBody DetalleCompra detalleCompra){
        try {
            if (compraRepository.findById(detalleCompra.getCompra().getId()).isEmpty())
                return new AddResponse<>("99", "La compra especificada no existe", null);

            if (productoRepository.findById(detalleCompra.getProducto().getId()).isEmpty())
                return new AddResponse<>("99", "El producto especificado no existe", null);

            DetalleCompra de = detalleCompraRepository.save(detalleCompra);
            return new AddResponse<>("01", null, de);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<DetalleCompra> ActualizarDetalleCompra(@Valid @RequestBody DetalleCompra detalleCompra){
        try {
            if (compraRepository.findById(detalleCompra.getCompra().getId()).isEmpty())
                return new UpdateResponse<>("99", "La compra especificada no existe", null);

            if (productoRepository.findById(detalleCompra.getProducto().getId()).isEmpty())
                return new UpdateResponse<>("99", "El producto especificado no existe", null);

            DetalleCompra de = detalleCompraRepository.save(detalleCompra);
            return new UpdateResponse<>("01", null, de);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarDetalleCompra(@PathVariable Long id){
        try{
            Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);

            if (detalleCompra.isEmpty())
                return new DeleteResponse("99", "El id del detalleCompra no existe", "El detalleCompra no fue eliminado");

            detalleCompraRepository.deleteById(id);
            return new DeleteResponse("01", null, "El detalleCompra fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
