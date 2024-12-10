package pe.edu.cibertec.DAW1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.DAW1.entity.Libros;

import java.util.List;

public interface LibroRepository extends CrudRepository<Libros, Integer> {

    @Query("SELECT l FROM Libros l WHERE LOWER(l.Titulo) LIKE LOWER(CONCAT('%', :nombre, '%')) OR LOWER(l.Autor) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Libros> findByNombreOrAutorContaining(@Param("nombre") String nombre);
}
