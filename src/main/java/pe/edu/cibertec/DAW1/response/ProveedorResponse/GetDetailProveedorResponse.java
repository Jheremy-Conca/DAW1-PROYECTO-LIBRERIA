package pe.edu.cibertec.DAW1.response.ProveedorResponse;


import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDetailDto;

public record GetDetailProveedorResponse(String code,
                                         String error,
                                         ProveedorDetailDto proveedorDetailDto) {

}