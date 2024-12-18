package pe.edu.cibertec.DAW1.service;


import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaCreateDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<CategoriaDto> getAllCategorias() throws Exception;

    Optional<CategoriaDetailDto> getDetailCategoria(int id) throws Exception;

    CategoriaDetailDto getCategoriaById(int id) throws Exception;

    Boolean updateCategoria(CategoriaDetailDto categoriaDetailDto) throws Exception;

    Boolean deleteCategoria(int id)  throws Exception;

    Boolean  createCategoria(CategoriaCreateDto categoriaCreateDto) throws Exception;

    List<CategoriaDto> searchCategoriasByNombre(String nombre) throws Exception;


}