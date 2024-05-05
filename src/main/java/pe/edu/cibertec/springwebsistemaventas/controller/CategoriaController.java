package pe.edu.cibertec.springwebsistemaventas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> ListarCategoria(){
    List<Categoria> getList = categoriaService.getAll();
        return new ResponseEntity<>(getList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarCategoria(@PathVariable Long id){
        Categoria categoria = categoriaService.getById(id).get();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> GuardarCategoria(@Valid @RequestBody Categoria categoria){
        Categoria cate = categoriaService.save(categoria);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> ActualizarCategoria(@Valid @RequestBody Categoria categoria){
        Categoria cate = categoriaService.save(categoria);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliminarCategoria(@PathVariable Long id){
        boolean b = categoriaService.delete(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

}
