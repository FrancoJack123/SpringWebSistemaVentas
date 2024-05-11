package pe.edu.cibertec.springwebsistemaventas.controllers.response;

import java.util.List;

public record FindAllResponse<T>(String code, String error, List<T> list) {
}
