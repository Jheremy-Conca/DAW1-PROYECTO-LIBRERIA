package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaCreateDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.CategoriaDto.CategoriaDto;
import pe.edu.cibertec.DAW1.service.CategoriaService;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listado")
    public String start(@RequestParam(value = "nombre", required = false) String nombre, Model model)  throws Exception{
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
    public String detail(@PathVariable Integer id, Model model)  throws Exception{
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model)  throws Exception{
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute CategoriaDetailDto categoriaDetailDto)  throws Exception{
        categoriaService.updateCategoria(categoriaDetailDto);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model)  throws Exception{
        CategoriaDetailDto categoriaDetailDto = categoriaService.getCategoriaById(id);
        if (categoriaDetailDto == null) {
            return "redirect:/categoria/listado";
        }
        model.addAttribute("categoria", categoriaDetailDto);
        return "categoria-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String deleteConfirm(@PathVariable Integer id)  throws Exception{
        categoriaService.deleteCategoria(id);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/create")
    public String create(Model model)  throws Exception{
        return "categoria-create";
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute CategoriaCreateDto categoriaCreateDto)  throws Exception{
        categoriaService.createCategoria(categoriaCreateDto);
        return "redirect:/categoria/listado";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createFilm(@RequestBody CategoriaCreateDto categoriaCreateDto) throws Exception {
        Boolean isCreated = categoriaService.createCategoria(categoriaCreateDto);

        if (isCreated) {
            return ResponseEntity.ok("Categoria creada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la categoria");
        }
    }
}
