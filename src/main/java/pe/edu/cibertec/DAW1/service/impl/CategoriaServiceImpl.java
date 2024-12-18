package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaCreateDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDto;
import pe.edu.cibertec.DAW1.entity.Categoria;
import pe.edu.cibertec.DAW1.repository.CategoriaRepository;
import pe.edu.cibertec.DAW1.service.CategoriaService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDto> getAllCategorias() {
        List<CategoriaDto> categorias = new ArrayList<>();
        Iterable<Categoria> iterable = categoriaRepository.findAll();
        iterable.forEach(categoria -> {
            CategoriaDto categoriaDto = new CategoriaDto(
                    categoria.getIdCategoria(),
                    categoria.getNombreCategoria(),
                    categoria.getDescripcion(),
                    categoria.getFechaRegistro()
            );
            categorias.add(categoriaDto);
        });
        return categorias;
    }

    @Override
    public Optional<CategoriaDetailDto> getDetailCategoria(int id)  {
        Optional<Categoria> optional =categoriaRepository.findById(id);
        return optional.map(categoria -> new CategoriaDetailDto(categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getDescripcion(),
                categoria.getFechaRegistro()));
    }

    @Override
    public CategoriaDetailDto getCategoriaById(int id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.map(categoria -> new CategoriaDetailDto(categoria.getIdCategoria(),
                        categoria.getNombreCategoria(),
                        categoria.getDescripcion(),
                        categoria.getFechaRegistro()))
                .orElse(null);
    }

    @Override
    public Boolean updateCategoria(CategoriaDetailDto categoriaDetailDto) {
        Optional<Categoria> optional = categoriaRepository.findById(categoriaDetailDto.IdCategoria());
        return optional.map(categoria -> {
            categoria.setNombreCategoria(categoriaDetailDto.NombreCategoria());
            categoria.setDescripcion(categoriaDetailDto.Descripcion());
            categoria.setFechaRegistro(categoriaDetailDto.FechaRegistro());

            LocalDateTime localDateTime = Instant.ofEpochMilli(new java.util.Date().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            categoria.setFechaRegistro(localDateTime);

            categoriaRepository.save(categoria);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean deleteCategoria(int id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.map(categoria -> {
            categoriaRepository.delete(categoria);
            return true;
        }).orElse(false);
    }
    @Override
    public Boolean createCategoria(CategoriaCreateDto categoriaCreateDto) {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(categoriaCreateDto.NombreCategoria());
        categoria.setDescripcion(categoriaCreateDto.Descripcion());

        // Set FechaRegistro to the current date and time if it's not provided in the DTO
        if (categoriaCreateDto.FechaRegistro() == null) {
            categoria.setFechaRegistro(LocalDateTime.now());
        } else {
            categoria.setFechaRegistro(categoriaCreateDto.FechaRegistro());
        }

        Categoria savedCategoria = categoriaRepository.save(categoria);

        return savedCategoria != null;  // Retorna true si la categoría fue guardada correctamente
    }

    @Override
    public List<CategoriaDto> searchCategoriasByNombre(String nombre) {
        List<Categoria> categorias = categoriaRepository.findByNombreCategoriaContaining(nombre);
        List<CategoriaDto> categoriasDto = new ArrayList<>();

        for (Categoria categoria : categorias) {
            categoriasDto.add(new CategoriaDto(
                    categoria.getIdCategoria(),
                    categoria.getNombreCategoria(),
                    categoria.getDescripcion(),
                    categoria.getFechaRegistro()
            ));
        }

        return categoriasDto;
    }
}
