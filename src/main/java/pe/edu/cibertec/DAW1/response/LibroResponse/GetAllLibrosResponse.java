package pe.edu.cibertec.DAW1.response.LibroResponse;

import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDto;
import pe.edu.cibertec.DAW1.entity.Libros;

import java.util.List;

public record GetAllLibrosResponse(String code,
                                       String error,
                                       List<Libros> libros) {

}