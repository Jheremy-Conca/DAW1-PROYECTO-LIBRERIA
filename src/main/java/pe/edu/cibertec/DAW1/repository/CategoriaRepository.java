package pe.edu.cibertec.DAW1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.DAW1.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends CrudRepository <Categoria, Integer> {
    @Query("SELECT c FROM Categoria c WHERE LOWER(c.NombreCategoria) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Categoria> findByNombreCategoriaContaining(@Param("nombre") String nombre);

}
