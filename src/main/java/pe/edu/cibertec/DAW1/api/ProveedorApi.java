package pe.edu.cibertec.DAW1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDto;
import pe.edu.cibertec.DAW1.response.ProveedorResponse.*;
import pe.edu.cibertec.DAW1.service.ProveedorService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorApi {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/all")
    public GetAllProveedoresResponse getAllUsers() {

        try {
            List<ProveedorDto> proveedores = proveedorService.getAllProveedor();
            if (!proveedores.isEmpty())
                return new GetAllProveedoresResponse("01", null, proveedores);
            else
                return new GetAllProveedoresResponse("02", "Proveedor not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new GetAllProveedoresResponse("99", "An error occurred, please try again", null);

        }

    }

    @GetMapping("/detail")
    public GetDetailProveedorResponse getDetailUser(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<ProveedorDetailDto> optional = proveedorService.getDetailProveedor(Integer.parseInt(id));
            return optional.map(user ->
                    new GetDetailProveedorResponse("01", null, user)
            ).orElse(
                    new GetDetailProveedorResponse("02", "Proveedor not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new GetDetailProveedorResponse("99", "An error occurred, please try again", null);

        }

    }

    @PutMapping("/update")
    public UpdateProveedorResponse updateCategoria(@RequestBody ProveedorDetailDto proveedorDetailDto) {

        try {
            if (proveedorService.updateProveedor(proveedorDetailDto))
                return new UpdateProveedorResponse("01", null);
            else
                return new UpdateProveedorResponse("02", "Update failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateProveedorResponse("99", "An error occurred, please try again");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteProveedorResponse deleteCar(@PathVariable String id) {

        try {
            if (proveedorService.deleteProveedor(Integer.parseInt(id)))
                return new DeleteProveedorResponse("01", null);
            else
                return new DeleteProveedorResponse("02", "Delete failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteProveedorResponse("99", "An error occurred, please try again");

        }

    }

    @PostMapping("/create")
    public CreateProveedorResponse createCar(@RequestBody ProveedorDetailDto proveedorDetailDto) {

        try {
            if (proveedorService.createProveedor(proveedorDetailDto))
                return new CreateProveedorResponse("01", null);
            else
                return new CreateProveedorResponse("02", "Create failed");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateProveedorResponse("99", "An error occurred, please try again");

        }

    }


}

