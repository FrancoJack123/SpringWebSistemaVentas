package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;
import pe.edu.cibertec.springwebsistemaventas.services.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Venta")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public ResponseEntity<?> ListarVentas(){
        List<Venta> getList = service.getAll();
        return new ResponseEntity<>(getList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarVenta(@PathVariable Long id){
        Venta venta = service.getById(id).get();
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> GuardarVenta(@Valid @RequestBody Venta venta){
        Venta ve = service.save(venta);
        return new ResponseEntity<>(ve, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> ActualizarVenta(@Valid @RequestBody Venta venta){
        Venta ve = service.save(venta);
        return new ResponseEntity<>(ve, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliminarVenta(@PathVariable Long id){
        boolean b = service.delete(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

}
