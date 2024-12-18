package pe.edu.cibertec.DAW1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.LibroDto.LibroDetailDto;
import pe.edu.cibertec.DAW1.entity.Libros;
import pe.edu.cibertec.DAW1.response.LibroResponse.*;
import pe.edu.cibertec.DAW1.service.LibroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroApi {

    @Autowired
    private LibroService libroService;

    @GetMapping("/all")
    public GetAllLibrosResponse getAllLibros() {
        try {
            List<Libros> libros = libroService.listAllLibros();
            return !libros.isEmpty()
                    ? new GetAllLibrosResponse("01", null, libros)
                    : new GetAllLibrosResponse("02", "Libros not found", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new GetAllLibrosResponse("99", "An error occurred, please try again", null);
        }
    }

    @GetMapping("/detail")
    public GetDetailLibroResponse getDetailLibro(@RequestParam(value = "id") int id) {
        try {
            Optional<LibroDetailDto> optional = libroService.getDetailLibro(Integer.parseInt(String.valueOf(id)));
            return optional.map(libro ->
                    new GetDetailLibroResponse("01", null, libro)
            ).orElse(
                    new GetDetailLibroResponse("02", "Proveedor not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new GetDetailLibroResponse("99", "An error occurred, please try again", null);

        }

    }

    @PostMapping("/create")
    public CreateLibroResponse createLibro(@RequestBody Libros libros) {
        try {
            libroService.saveLibro(libros);
            return new CreateLibroResponse("01", "Libro created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateLibroResponse("99", "An error occurred, please try again");
        }
    }

    @PutMapping("/update")
    public UpdateLibroResponse updateLibro(@RequestBody Libros libros) {
        try {
            libroService.updateLibro(libros);
            return new UpdateLibroResponse("01", "Libro updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateLibroResponse("99", "An error occurred, please try again");
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteLibroResponse deleteLibro(@PathVariable int id) {
        try {
            if (libroService.deleteById(id)) {
                return new DeleteLibroResponse("01", "Libro deleted successfully");
            } else {
                return new DeleteLibroResponse("02", "Delete failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteLibroResponse("99", "An error occurred, please try again");
        }
    }
}
