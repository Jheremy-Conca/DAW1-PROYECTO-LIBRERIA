package pe.edu.cibertec.DAW1.response.CategoriaResponse;

import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDto;

import java.util.List;

public record GetAllCategoriasResponse(String code,
                                       String error,
                                       List<CategoriaDto> categoria) {

}