package pe.edu.cibertec.DAW1.dto;

import java.time.LocalDateTime;

public record CategoriaDetailDto(Integer IdCategoria,
                                 String NombreCategoria,
                                 String Descripcion,
                                 LocalDateTime FechaRegistro) {

}
