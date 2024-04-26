package pe.edu.cibertec.springwebsistemaventas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.VentaRepository;
import pe.edu.cibertec.springwebsistemaventas.services.VentaService;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

    @Override
    public List<Venta> getAll() {
        return repository.findAll();
    }

    @Override
    public Venta save(Venta venta) {
        return repository.save(venta);
    }

    @Override
    public Optional<Venta> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
