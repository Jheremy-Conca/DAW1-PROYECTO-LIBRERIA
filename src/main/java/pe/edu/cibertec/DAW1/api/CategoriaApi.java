package pe.edu.cibertec.DAW1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaCreateDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDto;
import pe.edu.cibertec.DAW1.response.CategoriaResponse.*;
import pe.edu.cibertec.DAW1.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaApi {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/all")
    public GetAllCategoriasResponse getAllUsers() {

        try {
            List<CategoriaDto> categorias = categoriaService.getAllCategorias();
            if (!categorias.isEmpty())
                return new GetAllCategoriasResponse("01", null, categorias);
            else
                return new GetAllCategoriasResponse("02", "Categoria not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new GetAllCategoriasResponse("99", "An error occurred, please try again", null);

        }

    }

    @GetMapping("/detail")
        public GetDetailCategoriaResponse getDetailUser(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<CategoriaDetailDto> optional = categoriaService.getDetailCategoria(Integer.parseInt(id));
            return optional.map(user ->
                    new GetDetailCategoriaResponse("01", null, user)
            ).orElse(
                    new GetDetailCategoriaResponse("02", "Categoria not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new GetDetailCategoriaResponse("99", "An error occurred, please try again", null);

        }

    }

    @PutMapping("/update")
    public UpdateCategoriaResponse updateCategoria(@RequestBody CategoriaDetailDto categoriaDetailDto) {

        try {
            if (categoriaService.updateCategoria(categoriaDetailDto))
                return new UpdateCategoriaResponse("01", null);
            else
                return new UpdateCategoriaResponse("02", "Update failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCategoriaResponse("99", "An error occurred, please try again");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteCategoriaResponse deleteCar(@PathVariable String id) {

        try {
            if (categoriaService.deleteCategoria(Integer.parseInt(id)))
                return new DeleteCategoriaResponse("01", null);
            else
                return new DeleteCategoriaResponse("02", "Delete failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCategoriaResponse("99", "An error occurred, please try again");

        }

    }

    @PostMapping("/create")
    public CreateCategoriaResponse createCar(@RequestBody CategoriaCreateDto categoriaCreateDto) {

        try {
            if (categoriaService.createCategoria(categoriaCreateDto))
                return new CreateCategoriaResponse("01", null);
            else
                return new CreateCategoriaResponse("02", "Create failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCategoriaResponse("99", "An error occurred, please try again");

        }

    }


}
