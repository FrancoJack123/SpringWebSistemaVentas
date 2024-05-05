package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.DetalleVenta;
import pe.edu.cibertec.springwebsistemaventas.repository.DetalleVentaRepository;
import pe.edu.cibertec.springwebsistemaventas.services.DetalleVentaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Override
    public List<DetalleVenta> getAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> getById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            detalleVentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
