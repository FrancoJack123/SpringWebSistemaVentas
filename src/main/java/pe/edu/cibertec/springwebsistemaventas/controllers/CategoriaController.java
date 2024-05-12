package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public FindAllResponse<Categoria> ListarCategorias(){
        List<Categoria> getList = categoriaRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Categoria> BuscarCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty())
            return new FindByIdResponse<>("99", "La categoria buscada no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, categoria.get());
    }

    @PostMapping
    public AddResponse<Categoria> GuardarCategoria(@Valid @RequestBody Categoria categoria){
        try {
            Categoria ca = categoriaRepository.save(categoria);
            return new AddResponse<>("01", null, ca);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Categoria> ActualizarCategoria(@Valid @RequestBody Categoria categoria){
        try {
            if (categoriaRepository.findById(categoria.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id de la categoria no se encuentra en la base de datos", categoria);

            Categoria ca = categoriaRepository.save(categoria);
            return new UpdateResponse<>("01", null, ca);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarCategoria(@PathVariable Long id){
        try{
            Optional<Categoria> categoria = categoriaRepository.findById(id);

            if (categoria.isEmpty())
                return new DeleteResponse("99", "El id de la categoria no existe", "La categoria no fue eliminada");

            categoriaRepository.deleteById(id);
            return new DeleteResponse("01", null, "La categoria fue eliminada");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
