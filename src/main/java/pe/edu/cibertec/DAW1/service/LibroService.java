package pe.edu.cibertec.DAW1.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.DAW1.dto.LibroDetailDto;
import pe.edu.cibertec.DAW1.dto.LibroDto;
import pe.edu.cibertec.DAW1.entity.Libros;

import java.util.List;

public interface LibroService {

    void save(Libros libros);
    List<Libros> listAll();
    void deleteById(int id);
    Libros listById(int id);
    List<Libros> buscarPorTituloOAutor(String nombre);

}
