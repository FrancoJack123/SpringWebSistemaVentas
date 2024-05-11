package pe.edu.cibertec.springwebsistemaventas.controllers.response;

public record AddResponse<T>(String code, String error, T entity) {
}
