package pe.edu.cibertec.springwebsistemaventas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.repository.ProductoRepository;
import pe.edu.cibertec.springwebsistemaventas.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Producto")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> ListarProductos(){
        List<Producto> getList = service.getAll();
        return new ResponseEntity<>(getList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long id){
        Producto producto = service.getById(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> GuardarProducto(@Valid @RequestBody Producto producto){
        Producto pro = service.save(producto);
        return new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> ActualizarProducto(@Valid @RequestBody Producto producto){
        Producto pro = service.save(producto);
        return new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliminarProducto(@PathVariable Long id){
        boolean b = service.delete(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
