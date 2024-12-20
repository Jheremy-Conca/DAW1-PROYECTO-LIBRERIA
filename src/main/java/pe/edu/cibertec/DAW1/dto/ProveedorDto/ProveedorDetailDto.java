package pe.edu.cibertec.DAW1.dto.ProveedorDto;

import java.time.LocalDateTime;

public record ProveedorDetailDto(Integer IdProveedor,
                                 String Nombre,
                                 String Telefono,
                                 String Email,
                                 String Direccion,
                                 LocalDateTime FechaRegistro) {

}
