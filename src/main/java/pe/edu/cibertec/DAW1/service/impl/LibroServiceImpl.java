package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.DAW1.entity.Libros;
import pe.edu.cibertec.DAW1.repository.LibroRepository;
import pe.edu.cibertec.DAW1.service.LibroService;
import java.util.List;


@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;


    @Override
    public void save(Libros meme) {
        libroRepository.save(meme);
    }

    @Override
    public List<Libros> listAll() {
        return libroRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libros listById(int id) {
        return libroRepository.findById(id).get();
    }

    // Método para buscar libros por título o autor
    public List<Libros> buscarPorTituloOAutor(String nombre) {
        return libroRepository.findByNombreOrAutorContaining(nombre);
    }


}
