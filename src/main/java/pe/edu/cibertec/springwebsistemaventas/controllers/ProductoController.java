package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.CategoriaRepository;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public FindAllResponse<Producto> ListarProductos(){
        List<Producto> getList = productoRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Producto> BuscarProducto(@PathVariable Long id){
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isEmpty())
            return new FindByIdResponse<>("99", "El producto buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, producto.get());
    }

    @PostMapping
    public AddResponse<Producto> GuardarProducto(@Valid @RequestBody Producto producto){
        try {
            if (categoriaRepository.findById(producto.getCategoria().getId()).isEmpty())
                return new AddResponse<>("99", "La categoria especificada no existe", null);

            Producto pro = productoRepository.save(producto);
            return new AddResponse<>("01", null, pro);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Producto> ActualizarProducto(@Valid @RequestBody Producto producto){
        try {
            if (productoRepository.findById(producto.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id producto no se encuentra en la base de datos", producto);

            Producto pro = productoRepository.save(producto);
            return new UpdateResponse<>("01", null, pro);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarProducto(@PathVariable Long id){
        try{
            Optional<Producto> producto = productoRepository.findById(id);

            if (producto.isEmpty())
                return new DeleteResponse("99", "El id del producto no existe", "El producto no fue eliminado");

            productoRepository.deleteById(id);
            return new DeleteResponse("01", null, "El producto fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
