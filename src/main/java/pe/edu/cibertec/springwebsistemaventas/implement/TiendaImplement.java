package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.Tienda;
import pe.edu.cibertec.springwebsistemaventas.repository.TiendaRepository;
import pe.edu.cibertec.springwebsistemaventas.services.TiendaServices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TiendaImplement implements TiendaServices {
    @Autowired
    private TiendaRepository tiendaRepo;

    @Override
    public List<Tienda> getAll() {
        return tiendaRepo.findAll();
    }

    @Override
    public Tienda save(Tienda tienda) {
        tienda.setFecha_registro(new Date());
        return tiendaRepo.save(tienda);
    }

    @Override
    public Optional<Tienda> getById(Long id) {
        return tiendaRepo.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try{
            tiendaRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
