package pe.edu.cibertec.DAW1.service;

import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDto;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<ProveedorDto> getAllProveedor() throws Exception;

    Optional<ProveedorDetailDto> getDetailProveedor(int id) throws Exception;

    ProveedorDetailDto getProveedorById(int id) throws Exception;

    Boolean updateProveedor(ProveedorDetailDto proveedorDetailDto) throws Exception;

    Boolean deleteProveedor(int id) throws Exception;

    Boolean createProveedor(ProveedorDetailDto proveedorDetailDto)throws Exception;

    List<ProveedorDto> searchProveedoresByNombre(String nombre) throws Exception;



}
