package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDetailDto;
import pe.edu.cibertec.DAW1.entity.Categoria;
import pe.edu.cibertec.DAW1.entity.Libros;
import pe.edu.cibertec.DAW1.entity.Proveedores;
import pe.edu.cibertec.DAW1.repository.CategoriaRepository;
import pe.edu.cibertec.DAW1.repository.LibroRepository;
import pe.edu.cibertec.DAW1.repository.ProveedorRepository;
import pe.edu.cibertec.DAW1.service.LibroService;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean saveLibro(Libros libros) {
        Libros libro = new Libros();
        libro.setTitulo(libros.getTitulo());
        libro.setAutor(libros.getAutor());
        libro.setGenero(libros.getGenero());
        libro.setPrecio(libros.getPrecio());
        libro.setEstado(libros.getEstado());
        libro.setAnoPublicacion(libros.getAnoPublicacion());
        libro.setStock(libros.getStock());
        libro.setImagenurl(libros.getImagenurl());
        libro.setFechaRegistro(libros.getFechaRegistro());

        // Retrieve Proveedor object
        Optional<Proveedores> proveedorOpt = proveedorRepository.findById(libros.getProveedor().getIdProveedor());
        if (proveedorOpt.isPresent()) {
            libro.setProveedor(proveedorOpt.get());
        } else {
            throw new RuntimeException("Proveedor not found");
        }

        // Retrieve Categoria object
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(libros.getCategoria().getIdCategoria());
        if (categoriaOpt.isPresent()) {
            libro.setCategoria(categoriaOpt.get());
        } else {
            throw new RuntimeException("Categoria not found");
        }

        // Save the entity
        libroRepository.save(libro);
        return true;
    }


    @Override
    public List<Libros> listAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        try {
            libroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Libros listById(int id) {
        Optional<Libros> libro = libroRepository.findById(id);
        return libro.orElse(null);
    }

    @Override
    public List<Libros> buscarPorTituloOAutor(String nombre) {
        return libroRepository.findByNombreOrAutorContaining(nombre);
    }

    @Override
    public Optional<LibroDetailDto> getDetailLibro(int id) throws Exception {
        Optional<Libros> optional = libroRepository.findById(id);
        return optional.map(libro -> new LibroDetailDto(libro.getIdLibro(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero(),
                libro.getPrecio(),
                libro.getEstado(),
                libro.getAnoPublicacion(),
                libro.getStock(),
                libro.getImagenurl(),
                libro.getFechaRegistro(),
                libro.getProveedor().getIdProveedor(),
                libro.getProveedor().getNombre(),
                libro.getCategoria().getIdCategoria(),
                libro.getCategoria().getNombreCategoria()));
    }

    @Override
    public Boolean updateLibro(Libros libros) throws Exception {
        Optional<Libros> optional = libroRepository.findById(libros.getIdLibro());
        return optional.map(libro -> {
            libro.setIdLibro(libros.getIdLibro());
            libro.setPrecio(libros.getPrecio());
            libro.setStock(libros.getStock());
            libro.setImagenurl(libros.getImagenurl());

            libroRepository.save(libro);
            return true;
        }).orElse(false);
    }

    @Override
    public LibroDetailDto getLibroById(int id) throws Exception {
        Optional<Libros> optional = libroRepository.findById(id);
        return optional.map(libro -> new LibroDetailDto(libro.getIdLibro(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getGenero(),
                        libro.getPrecio(),
                        libro.getEstado(),
                        libro.getAnoPublicacion(),
                        libro.getStock(),
                        libro.getImagenurl(),
                        libro.getFechaRegistro(),
                        libro.getProveedor().getIdProveedor(),
                        libro.getProveedor().getNombre(),
                        libro.getCategoria().getIdCategoria(),
                        libro.getCategoria().getNombreCategoria()))
                .orElse(null);
    }
}
