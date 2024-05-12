package pe.edu.cibertec.springwebsistemaventas.controllers.response;

import java.util.List;

public record FindByIdResponse<T>(String code, String error, T entity) {
}
