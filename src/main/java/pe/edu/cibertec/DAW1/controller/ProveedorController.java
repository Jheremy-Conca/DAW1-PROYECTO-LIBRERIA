package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.ProveedorDetailDto;
import pe.edu.cibertec.DAW1.dto.ProveedorDto;
import pe.edu.cibertec.DAW1.service.ProveedorService;

import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listado")
    public String start(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
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
    public String detail(@PathVariable Integer id, Model model) {
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute ProveedorDetailDto proveedorDetailDto) {
        proveedorService.updateProveedor(proveedorDetailDto);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        ProveedorDetailDto proveedorDetailDto = proveedorService.getProveedorById(id);
        if (proveedorDetailDto == null) {
            return "redirect:/proveedor/listado";
        }
        model.addAttribute("proveedor", proveedorDetailDto);
        return "proveedor-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String deleteConfirm(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "proveedor-create";
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute ProveedorDetailDto proveedorDetailDto) {
        proveedorService.createProveedor(proveedorDetailDto);
        return "redirect:/proveedor/listado";
    }

    @PostMapping("/create")
    public ResponseEntity<ProveedorDetailDto> createProveedor(@RequestBody ProveedorDetailDto proveedorDetailDto) {
        ProveedorDetailDto createProveedor = proveedorService.createProveedor(proveedorDetailDto);
        return ResponseEntity.ok(createProveedor);
    }
}
