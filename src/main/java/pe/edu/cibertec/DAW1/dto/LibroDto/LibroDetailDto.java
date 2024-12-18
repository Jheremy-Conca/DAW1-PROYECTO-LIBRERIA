package pe.edu.cibertec.DAW1.dto.LibroDto;

import java.time.LocalDateTime;

public record LibroDetailDto(Integer IdLibro,
                             String Titulo,
                             String Autor,
                             String Genero,
                             Double Precio,
                             String Estado,
                             Integer AnoPublicacion,
                             Integer Stock,
                             String ImagenURL,
                             LocalDateTime FechaRegistro,
                             Integer IdProveedor,
                             String NombreCategoria,
                             Integer IdCategoria,
                             String NombreProveedor) {


}
