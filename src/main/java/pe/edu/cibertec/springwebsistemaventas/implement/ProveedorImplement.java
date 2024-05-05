package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.Proveedor;
import pe.edu.cibertec.springwebsistemaventas.repository.ProveedorRepository;
import pe.edu.cibertec.springwebsistemaventas.services.ProveedorServices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorImplement implements ProveedorServices {

    @Autowired
    private ProveedorRepository proveedorRepo;
    @Override
    public List<Proveedor> getAll() {
        return proveedorRepo.findAll();
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        proveedor.setFecha_registro(new Date());
        return proveedorRepo.save(proveedor);
    }

    @Override
    public Optional<Proveedor> getById(Long id) {
        return proveedorRepo.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try{
            proveedorRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
