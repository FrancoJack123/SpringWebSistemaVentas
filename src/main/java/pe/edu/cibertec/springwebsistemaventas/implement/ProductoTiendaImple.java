package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.ProductoTienda;
import pe.edu.cibertec.springwebsistemaventas.repository.ProductoTiendaRepository;
import pe.edu.cibertec.springwebsistemaventas.services.ProductoTiendaServices;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoTiendaImple implements ProductoTiendaServices {
    @Autowired
    private ProductoTiendaRepository productoTiendaRepository;
    @Override
    public List<ProductoTienda> getAll() {
        return productoTiendaRepository.findAll();
    }

    @Override
    public ProductoTienda save(ProductoTienda productoTienda) {
        return productoTiendaRepository.save(productoTienda);
    }

    @Override
    public Optional<ProductoTienda> getById(Long id) {
        return productoTiendaRepository.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            productoTiendaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
