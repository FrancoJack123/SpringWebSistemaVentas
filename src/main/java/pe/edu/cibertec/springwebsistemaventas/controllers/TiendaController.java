package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Tienda;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.TiendaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/tienda")
public class TiendaController {

    @Autowired
    private TiendaRepository tiendaRepository;

    @GetMapping
    public FindAllResponse<Tienda> ListarTiendas(){
        List<Tienda> getList = tiendaRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Tienda> BuscarTienda(@PathVariable Long id){
        Optional<Tienda> tienda = tiendaRepository.findById(id);
        if (tienda.isEmpty())
            return new FindByIdResponse<>("99", "La tienda buscada no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, tienda.get());
    }

    @PostMapping
    public AddResponse<Tienda> GuardarTienda(@Valid @RequestBody Tienda tienda){
        try {
            Tienda ca = tiendaRepository.save(tienda);
            return new AddResponse<>("01", null, ca);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Tienda> ActualizarTienda(@Valid @RequestBody Tienda tienda){
        try {
            if (tiendaRepository.findById(tienda.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id de la tienda no se encuentra en la base de datos", tienda);

            Tienda ca = tiendaRepository.save(tienda);
            return new UpdateResponse<>("01", null, ca);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarTienda(@PathVariable Long id){
        try{
            Optional<Tienda> tienda = tiendaRepository.findById(id);

            if (tienda.isEmpty())
                return new DeleteResponse("99", "El id de la tienda no existe", "La tienda no fue eliminada");

            tiendaRepository.deleteById(id);
            return new DeleteResponse("01", null, "La tienda fue eliminada");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
