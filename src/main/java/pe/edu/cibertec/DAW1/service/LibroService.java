package pe.edu.cibertec.DAW1.service;

import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDetailDto;
import pe.edu.cibertec.DAW1.entity.Libros;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    boolean saveLibro(Libros libros)  throws Exception;

    List<Libros> listAllLibros()  throws Exception;
    boolean deleteById(int id)  throws Exception;
    Libros listById(int id)  throws Exception;
    List<Libros> buscarPorTituloOAutor(String nombre) throws Exception;
    Optional<LibroDetailDto> getDetailLibro(int id) throws Exception;
    Boolean updateLibro(Libros libros) throws Exception;
    LibroDetailDto getLibroById(int id) throws Exception;
}
