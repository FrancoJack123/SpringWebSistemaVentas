package pe.edu.cibertec.springwebsistemaventas.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.repository.CategoriaRepository;
import pe.edu.cibertec.springwebsistemaventas.services.CategoriaService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaImp implements CategoriaService {
    @Autowired
    private CategoriaRepository repoCate;
    @Override
    public List<Categoria> getAll() {
        return repoCate.findAll();
    }

    @Override
    public Categoria save(Categoria categoria) {
        return repoCate.save(categoria);
    }

    @Override
    public Optional<Categoria> getById(Long id) {
        return repoCate.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            repoCate.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
