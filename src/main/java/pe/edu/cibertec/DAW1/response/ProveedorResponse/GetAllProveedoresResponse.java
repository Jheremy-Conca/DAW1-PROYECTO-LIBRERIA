package pe.edu.cibertec.DAW1.response.ProveedorResponse;

import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDto;

import java.util.List;

public record GetAllProveedoresResponse(String code,
                                        String error,
                                        List<ProveedorDto> proveedor) {

}