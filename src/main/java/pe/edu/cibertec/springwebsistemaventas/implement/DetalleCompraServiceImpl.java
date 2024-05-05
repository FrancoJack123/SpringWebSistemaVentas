package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.DetalleCompra;
import pe.edu.cibertec.springwebsistemaventas.repository.DetalleCompraRepository;
import pe.edu.cibertec.springwebsistemaventas.services.DetalleCompraService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;
    @Override
    public List<DetalleCompra> getAll() {
        return detalleCompraRepository.findAll();
    }

    @Override
    public DetalleCompra save(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    public Optional<DetalleCompra> getById(Long id) {
        return detalleCompraRepository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            detalleCompraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
