package pe.edu.cibertec.DAW1.service;

import pe.edu.cibertec.DAW1.dto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDto> getAllCategorias();

    CategoriaDetailDto getCategoriaById(int id);

    Boolean updateCategoria(CategoriaDetailDto categoriaDetailDto);

    Boolean deleteCategoria(int id);

    CategoriaDetailDto createCategoria(CategoriaDetailDto categoriaDetailDto);

    List<CategoriaDto> searchCategoriasByNombre(String nombre);


}