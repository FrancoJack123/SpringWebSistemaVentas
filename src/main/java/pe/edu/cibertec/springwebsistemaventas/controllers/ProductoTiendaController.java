package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.ProductoTienda;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProductoTiendaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/producto-tienda")
public class ProductoTiendaController {

    @Autowired
    private ProductoTiendaRepository productoTiendaRepository;

    @GetMapping
    public FindAllResponse<ProductoTienda> ListarProductoTienda(){
        List<ProductoTienda> getList = productoTiendaRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<ProductoTienda> BuscarProducto(@PathVariable Long id){
        Optional<ProductoTienda> productoTienda = productoTiendaRepository.findById(id);
        if (productoTienda.isEmpty())
            return new FindByIdResponse<>("99", "El productoTienda buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, productoTienda.get());
    }

    @PostMapping
    public AddResponse<ProductoTienda> GuardarProducto(@Valid @RequestBody ProductoTienda productoTienda){
        try {
            ProductoTienda pro = productoTiendaRepository.save(productoTienda);
            return new AddResponse<>("01", null, pro);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<ProductoTienda> ActualizarProducto(@Valid @RequestBody ProductoTienda producto){
        try {
            if (productoTiendaRepository.findById(producto.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id ProductoTienda no se encuentra en la base de datos", producto);

            ProductoTienda pro = productoTiendaRepository.save(producto);
            return new UpdateResponse<>("01", null, pro);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarProducto(@PathVariable Long id){
        try{
            Optional<ProductoTienda> producto = productoTiendaRepository.findById(id);

            if (producto.isEmpty())
                return new DeleteResponse("99", "El id del ProductoTienda no existe", "El ProductoTienda no fue eliminado");

            productoTiendaRepository.deleteById(id);
            return new DeleteResponse("01", null, "El ProductoTienda fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
