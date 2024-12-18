package pe.edu.cibertec.DAW1.response.CategoriaResponse;


import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;

public record GetDetailCategoriaResponse(String code,
                                         String error,
                                         CategoriaDetailDto categoriaDetailDto) {

}