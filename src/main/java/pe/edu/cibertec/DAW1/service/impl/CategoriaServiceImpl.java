package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAW1.dto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto;
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
    public CategoriaDetailDto createCategoria(CategoriaDetailDto categoriaDetailDto) {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(categoriaDetailDto.NombreCategoria());
        categoria.setDescripcion(categoriaDetailDto.Descripcion());

        // Set FechaRegistro to the current date and time if it's not provided in the DTO
        if (categoriaDetailDto.FechaRegistro() == null) {
            categoria.setFechaRegistro(LocalDateTime.now());
        } else {
            categoria.setFechaRegistro(categoriaDetailDto.FechaRegistro());
        }

        Categoria savedCategoria = categoriaRepository.save(categoria);

        return new CategoriaDetailDto(
                savedCategoria.getIdCategoria(),
                savedCategoria.getNombreCategoria(),
                savedCategoria.getDescripcion(),
                savedCategoria.getFechaRegistro()
        );
    }

}
