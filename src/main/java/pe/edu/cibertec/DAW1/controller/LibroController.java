package pe.edu.cibertec.DAW1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.DAW1.dto.CategoriaDetailDto;
import pe.edu.cibertec.DAW1.dto.LibroDetailDto;
import pe.edu.cibertec.DAW1.dto.LibroDto;
import pe.edu.cibertec.DAW1.service.IUploadFileService;
import pe.edu.cibertec.DAW1.service.LibroService;

import java.util.List;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/listado")
    public String start(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<LibroDto> libros;
        if (nombre != null && !nombre.trim().isEmpty()) {
            libros = libroService.searchLibroByNombre(nombre);
        } else {
            libros = libroService.getAllLibro();
        }
        model.addAttribute("libros", libros);
        model.addAttribute("nombreBusqueda", nombre);
        return "libro";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        LibroDetailDto libroDetailDto = libroService.getLibroById(id);
        model.addAttribute("libro", libroDetailDto);
        return "libro-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        LibroDetailDto libroDetailDto = libroService.getLibroById(id);
        model.addAttribute("libro", libroDetailDto);
        return "libro-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute LibroDetailDto libroDetailDto) {
        libroService.updateLibro(libroDetailDto);
        return "redirect:/libro/listado";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        LibroDetailDto libroDetailDto = libroService.getLibroById(id);
        if (libroDetailDto == null) {
            return "redirect:/libro/listado";
        }
        model.addAttribute("libro", libroDetailDto);
        return "libro-delete-confirm";
    }

    @PostMapping("/delete-confirm/{id}")
    public String deleteConfirm(@PathVariable Integer id) {
        libroService.deleteLibro(id);
        return "redirect:/libro/listado";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "libro-create";
    }

    @PostMapping("/create-confirm")
    public String createConfirm(@ModelAttribute LibroDetailDto libroDetailDto) {
        libroService.createLibro(libroDetailDto);
        return "redirect:/libro/listado";
    }

    @PostMapping("/create")
    public ResponseEntity<LibroDetailDto> createLibro(@RequestBody LibroDetailDto libroDetailDto) {
        LibroDetailDto createLibro = libroService.createLibro(libroDetailDto);
        return ResponseEntity.ok(createLibro);
    }
}
