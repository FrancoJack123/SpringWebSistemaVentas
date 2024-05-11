package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Compra;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;
    @GetMapping
    public FindAllResponse<Compra> ListarCompras(){
        List<Compra> getList = compraRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Compra> BuscarCompra(@PathVariable Long id){
        Optional<Compra> compra = compraRepository.findById(id);
        if (compra.isEmpty())
            return new FindByIdResponse<>("99", "La compra buscada no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, compra.get());
    }

    @PostMapping
    public AddResponse<Compra> GuardarCompra(@Valid @RequestBody Compra compra){
        try {
            Compra va = compraRepository.save(compra);
            return new AddResponse<>("01", "La compra fue registrada", null);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Compra> ActualizarCompra(@Valid @RequestBody Compra compra){
        try {
            Compra va = compraRepository.save(compra);
            return new UpdateResponse<>("01", "La compra fue actualizada", null);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarCompra(@PathVariable Long id){
        try{
            Optional<Compra> compra = compraRepository.findById(id);

            if (compra.isEmpty())
                return new DeleteResponse("99", "El id de la compra no existe", "La compra no fue eliminada");

            compraRepository.deleteById(id);
            return new DeleteResponse("01", null, "La compra fue eliminada");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
