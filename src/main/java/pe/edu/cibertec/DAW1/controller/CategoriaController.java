package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto;
import pe.edu.cibertec.DAW1.service.CategoriaService;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listado")
    public String start(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<CategoriaDto> categorias;
        if (nombre != null && !nombre.trim().isEmpty()) {
            categorias = categoriaService.searchCategoriasByNombre(nombre);
        } else {
            categorias = categoriaService.getAllCategorias();
        }
        model.addAttribute("categorias", categorias);
        model.addAttribute("nombreBusqueda", nombre);
        return "categoria";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute CategoriaDetailDto categoriaDetailDto) {
        categoriaService.updateCategoria(categoriaDetailDto);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        if (categoriaDetailDto == null) {
            return "redirect:/categoria/listado";
        }
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String deleteConfirm(@PathVariable Integer id) {
        categoriaService.deleteCategoria(id);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "categoria-create";
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute CategoriaDetailDto categoriaDetailDto) {
        categoriaService.createCategoria(categoriaDetailDto);
        return "redirect:/categoria/listado";
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaDetailDto> createFilm(@RequestBody CategoriaDetailDto categoriaDetailDto) {
        CategoriaDetailDto createCategoria = categoriaService.createCategoria(categoriaDetailDto);
        return ResponseEntity.ok(createCategoria);
    }
}
