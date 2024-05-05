package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.Compra;
import pe.edu.cibertec.springwebsistemaventas.repository.CompraRepository;
import pe.edu.cibertec.springwebsistemaventas.services.CompraService;

import java.util.List;
import java.util.Optional;

@Service
public class CompraImplements implements CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Override
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    @Override
    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Optional<Compra> getById(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            compraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
