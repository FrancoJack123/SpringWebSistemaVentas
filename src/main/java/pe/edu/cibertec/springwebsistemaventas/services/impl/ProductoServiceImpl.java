package pe.edu.cibertec.springwebsistemaventas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.ProductoRepository;
import pe.edu.cibertec.springwebsistemaventas.services.ProductoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> getAll() {
        return repository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
       producto.setFecha_registro(new Date());
       return repository.save(producto);
    }

    @Override
    public Optional<Producto> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
