package pe.edu.cibertec.DAW1.response.LibroResponse;

import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDetailDto;
import pe.edu.cibertec.DAW1.entity.Libros;

public record GetDetailLibroResponse(String code,
                                     String error,
                                     LibroDetailDto libroDetailDto) {

}