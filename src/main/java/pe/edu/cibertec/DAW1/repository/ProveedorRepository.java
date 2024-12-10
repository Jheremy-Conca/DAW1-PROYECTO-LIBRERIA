package pe.edu.cibertec.DAW1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.DAW1.entity.Proveedores;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<Proveedores,Integer> {
    @Query("SELECT p FROM Proveedores p WHERE LOWER(p.Nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Proveedores> findByNombreProveedorContaining(@Param("nombre") String nombre);

}
