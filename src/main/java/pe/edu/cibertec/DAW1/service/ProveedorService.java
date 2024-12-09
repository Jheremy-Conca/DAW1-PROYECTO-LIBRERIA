package pe.edu.cibertec.DAW1.service;

import pe.edu.cibertec.DAW1.dto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDto> getAllProveedor();

    ProveedorDetailDto getProveedorById(int id);

    Boolean updateProveedor(ProveedorDetailDto proveedorDetailDto);

    Boolean deleteProveedor(int id);

    ProveedorDetailDto createProveedor(ProveedorDetailDto proveedorDetailDto);
    List<ProveedorDto> searchProveedoresByNombre(String nombre);



}
