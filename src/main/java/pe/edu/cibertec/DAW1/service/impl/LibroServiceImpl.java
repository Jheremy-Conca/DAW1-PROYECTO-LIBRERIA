package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAW1.dto.LibroDetailDto;
import pe.edu.cibertec.DAW1.dto.LibroDto;
import pe.edu.cibertec.DAW1.entity.Libros;
import pe.edu.cibertec.DAW1.entity.Categoria;
import pe.edu.cibertec.DAW1.entity.Proveedores;
import pe.edu.cibertec.DAW1.repository.CategoriaRepository;
import pe.edu.cibertec.DAW1.repository.LibroRepository;
import pe.edu.cibertec.DAW1.repository.ProveedorRepository;
import pe.edu.cibertec.DAW1.service.LibroService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<LibroDto> getAllLibro() {
        List<LibroDto> librosDto = new ArrayList<>();
        Iterable<Libros> iterable = libroRepository.findAll();
        iterable.forEach(libros -> {
            LibroDto libroDto = new LibroDto(
                    libros.getIdLibro(),
                    libros.getTitulo(),
                    libros.getAutor(),
                    libros.getStock().doubleValue(),
                    libros.getEstado()
            );
            librosDto.add(libroDto);
        });
        return librosDto;
    }

    @Override
    public LibroDetailDto getLibroById(int id) {
        Optional<Libros> optional = libroRepository.findById(id);
        return optional.map(libros -> new LibroDetailDto(
                        libros.getIdLibro(),
                        libros.getTitulo(),
                        libros.getAutor(),
                        libros.getGenero(),
                        libros.getPrecio(),
                        libros.getEstado(),
                        libros.getAnoPublicacion(),
                        libros.getStock(),
                        libros.getImagenURL(),
                        libros.getFechaRegistro(),
                        libros.getCategoria().getIdCategoria(),
                        libros.getCategoria().getNombreCategoria(),
                        libros.getProveedor().getIdProveedor(),
                        libros.getProveedor().getNombre()))
                .orElse(null);
    }

    @Override
    public Boolean updateLibro(LibroDetailDto libroDetailDto) {
        Optional<Libros> optional = libroRepository.findById(libroDetailDto.IdLibro());
        return optional.map(libros -> {
            libros.setTitulo(libroDetailDto.Titulo());
            libros.setAutor(libroDetailDto.Autor());
            libros.setGenero(libroDetailDto.Genero());
            libros.setPrecio(libroDetailDto.Precio());
            libros.setEstado(libroDetailDto.Estado());
            libros.setAnoPublicacion(libroDetailDto.AnoPublicacion());
            libros.setStock(libroDetailDto.Stock());
            libros.setImagenURL(libroDetailDto.ImagenURL());

            // Si la FechaRegistro es nula, la asignamos al momento actual
            if (libroDetailDto.FechaRegistro() == null) {
                libros.setFechaRegistro(LocalDateTime.now());
            } else {
                libros.setFechaRegistro(libroDetailDto.FechaRegistro());
            }
            libroRepository.save(libros);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean deleteLibro(int id) {
        Optional<Libros> optional = libroRepository.findById(id);
        return optional.map(libros -> {
            libroRepository.delete(libros);
            return true;
        }).orElse(false);
    }

    @Override
    public LibroDetailDto createLibro(LibroDetailDto libroDetailDto) {
        Libros libros = new Libros();
        libros.setTitulo(libroDetailDto.Titulo());
        libros.setAutor(libroDetailDto.Autor());
        libros.setGenero(libroDetailDto.Genero());
        libros.setPrecio(libroDetailDto.Precio());
        libros.setEstado(libroDetailDto.Estado());
        libros.setAnoPublicacion(libroDetailDto.AnoPublicacion());
        libros.setStock(libroDetailDto.Stock());
        libros.setImagenURL(libroDetailDto.ImagenURL());

        // Si FechaRegistro es nula, la asignamos al momento actual
        if (libroDetailDto.FechaRegistro() == null) {
            libros.setFechaRegistro(LocalDateTime.now());
        } else {
            libros.setFechaRegistro(libroDetailDto.FechaRegistro());
        }

        // Asignar la categoría al libro
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(libroDetailDto.IdCategoria());
        if (categoriaOptional.isPresent()) {
            libros.setCategoria(categoriaOptional.get());
        } else {
            // Si no se encuentra la categoría, se crea una nueva categoría
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setIdCategoria(libroDetailDto.IdCategoria());
            libros.setCategoria(nuevaCategoria);
        }
        // Asignar la categoría al libro
        Optional<Proveedores> proveedoresOptional = proveedorRepository.findById(libroDetailDto.IdCategoria());
        if (categoriaOptional.isPresent()) {
            libros.setProveedor(proveedoresOptional.get());
        } else {
            // Si no se encuentra la categoría, se crea una nueva categoría
            Proveedores nuevoProveedor = new Proveedores();
            nuevoProveedor.setIdProveedor(libroDetailDto.IdProveedor());
            libros.setProveedor(nuevoProveedor);
        }
        // Guardar el libro
        Libros savedLibro = libroRepository.save(libros);



        // Devolver el DTO con los datos del libro guardado
        return new LibroDetailDto(
                savedLibro.getIdLibro(),
                savedLibro.getTitulo(),
                savedLibro.getAutor(),
                savedLibro.getGenero(),
                savedLibro.getPrecio(),
                savedLibro.getEstado(),
                savedLibro.getAnoPublicacion(),
                savedLibro.getStock(),
                savedLibro.getImagenURL(),
                savedLibro.getFechaRegistro(),
                savedLibro.getCategoria().getIdCategoria(),
                savedLibro.getCategoria().getNombreCategoria(),
                savedLibro.getProveedor().getIdProveedor(),
                savedLibro.getProveedor().getNombre()
        );
    }

    @Override
    public List<LibroDto> searchLibroByNombre(String nombre) {
        List<Libros> libros = libroRepository.findByNombreOrAutorContaining(nombre);
        List<LibroDto> libroDto = new ArrayList<>();

        for (Libros libro : libros) {
            libroDto.add(new LibroDto(
                    libro.getIdLibro(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getStock().doubleValue(),
                    libro.getEstado()
            ));
        }

        return libroDto;
    }
}
