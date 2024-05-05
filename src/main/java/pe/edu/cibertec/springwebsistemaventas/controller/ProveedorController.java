package pe.edu.cibertec.springwebsistemaventas.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.entity.Proveedor;
import pe.edu.cibertec.springwebsistemaventas.services.ProveedorServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorServices prodServices;

    @GetMapping
    public ResponseEntity<?> ListarProveedor(){
        List<Proveedor> getList = prodServices.getAll();
        return new ResponseEntity<>(getList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarProveedor(@PathVariable Long id){
        Proveedor proveedor = prodServices.getById(id).get();
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> GuardarProveedor(@Valid @RequestBody Proveedor proveedor){
        Proveedor prove = prodServices.save(proveedor);
        return new ResponseEntity<>(prove, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> ActualizarProveedor(@Valid @RequestBody Proveedor proveedor){
        Proveedor prove = prodServices.save(proveedor);
        return new ResponseEntity<>(prove, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliminarProveedor(@PathVariable Long id){
        boolean b = prodServices.delete(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
