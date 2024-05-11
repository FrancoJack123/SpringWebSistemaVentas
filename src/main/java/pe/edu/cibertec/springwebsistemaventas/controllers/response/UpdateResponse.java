package pe.edu.cibertec.springwebsistemaventas.controllers.response;

public record UpdateResponse<T>(String code, String error, T entity) {
}
