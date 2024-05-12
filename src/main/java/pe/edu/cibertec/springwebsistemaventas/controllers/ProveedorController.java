package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Proveedor;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public FindAllResponse<Proveedor> ListarProveedores(){
        List<Proveedor> getList = proveedorRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<Proveedor> BuscarProveedor(@PathVariable Long id){
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isEmpty())
            return new FindByIdResponse<>("99", "El proveedor buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, proveedor.get());
    }

    @PostMapping
    public AddResponse<Proveedor> GuardarProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            Proveedor pro = proveedorRepository.save(proveedor);
            return new AddResponse<>("01", null, pro);
        } catch (Exception e) {
            return new AddResponse<>("99", e.getMessage(), null);
        }
    }

    @PutMapping
    public UpdateResponse<Proveedor> ActualizarProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            if (proveedorRepository.findById(proveedor.getId()).isEmpty())
                return new UpdateResponse<>("99", "El id proveedor no se encuentra en la base de datos", proveedor);

            Proveedor pro = proveedorRepository.save(proveedor);
            return new UpdateResponse<>("01", null, pro);
        } catch (Exception e) {
            return new UpdateResponse<>("99", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public DeleteResponse EliminarProveedor(@PathVariable Long id){
        try{
            Optional<Proveedor> proveedor = proveedorRepository.findById(id);

            if (proveedor.isEmpty())
                return new DeleteResponse("99", "El id del proveedor no existe", "El proveedor no fue eliminado");

            proveedorRepository.deleteById(id);
            return new DeleteResponse("01", null, "El proveedor fue eliminado");
        }catch (Exception e){
            return new DeleteResponse("01", e.getMessage(), null);
        }
    }
}
