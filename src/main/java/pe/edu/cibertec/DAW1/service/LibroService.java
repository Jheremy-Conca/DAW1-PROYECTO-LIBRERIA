package pe.edu.cibertec.DAW1.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.DAW1.dto.LibroDetailDto;
import pe.edu.cibertec.DAW1.dto.LibroDto;
import pe.edu.cibertec.DAW1.entity.Libros;

import java.util.List;

public interface LibroService {

    List<LibroDto> getAllLibro();

    LibroDetailDto getLibroById(int id);

    Boolean updateLibro(LibroDetailDto libroDetailDto);

    Boolean deleteLibro(int id);

    LibroDetailDto createLibro(LibroDetailDto libroDetailDto);

    List<LibroDto> searchLibroByNombre(String nombre);



}
