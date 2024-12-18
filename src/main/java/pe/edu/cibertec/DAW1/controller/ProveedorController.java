package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto.ProveedorDto;
import pe.edu.cibertec.DAW1.service.ProveedorService;

import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listado")
    public String start(@RequestParam(value = "nombre", required = false) String nombre, Model model) throws Exception{
        List<ProveedorDto> proveedores;
        if (nombre != null && !nombre.trim().isEmpty()) {
            proveedores = proveedorService.searchProveedoresByNombre(nombre);
        } else {
            proveedores = proveedorService.getAllProveedor();
        }
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("nombreBusqueda", nombre);
        return "proveedor";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) throws Exception{
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) throws Exception{
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute ProveedorDetailDto proveedorDetailDto) throws Exception{
        proveedorService.updateProveedor(proveedorDetailDto);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model)throws Exception {
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        if (proveedorDetailDto == null) {
            return "redirect:/proveedor/listado";
        }
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String deleteConfirm(@PathVariable Integer id) throws Exception{
        proveedorService.deleteProveedor(id);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "proveedor-create";
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute ProveedorDetailDto proveedorDetailDto) throws Exception{
        proveedorService.createProveedor(proveedorDetailDto);
        return "redirect:/proveedor/listado";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProveedor(@RequestBody ProveedorDetailDto proveedorDetailDto) throws Exception{
        Boolean isCreated = proveedorService.createProveedor(proveedorDetailDto);

        if (isCreated) {
            return ResponseEntity.ok("Categoria creada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la categoria");
        }
    }
}
