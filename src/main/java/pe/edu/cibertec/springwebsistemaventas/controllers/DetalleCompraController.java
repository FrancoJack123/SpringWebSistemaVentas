package pe.edu.cibertec.springwebsistemaventas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.springwebsistemaventas.controllers.response.*;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.DetalleCompra;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.DetalleVenta;
import pe.edu.cibertec.springwebsistemaventas.persistence.repository.DetalleCompraRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/detalle-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @GetMapping
    public FindAllResponse<DetalleCompra> ListarDetalleCompras(){
        List<DetalleCompra> getList = detalleCompraRepository.findAll();

        if(getList.isEmpty())
            return new FindAllResponse<>("01", "La lista se encuentra vacia", null);

        return new FindAllResponse<>("01", null, getList);
    }

    @GetMapping("/{id}")
    public FindByIdResponse<DetalleCompra> BuscarDetalleCompra(@PathVariable Long id){
        Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty())
            return new FindByIdResponse<>("99", "El detalleCompra buscado no se encuentra en la base de datos", null);
        return new FindByIdResponse<>("01", null, detalleCompra.get());
    }
}
